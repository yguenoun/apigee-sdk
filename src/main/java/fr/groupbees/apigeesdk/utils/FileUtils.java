package fr.groupbees.apigeesdk.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    public static void delete(Path path) throws IOException {
        if (path.toFile().isDirectory()) {
            String[] children = path.toFile().list();
            if (children != null) {
                for (String child : children) {
                    delete(path.resolve(child));
                }
            }
        }
        Files.delete(path);
    }

    public static Path zipPackage(final Path bundleZip, final Path srcPath, final String rootDirectory) throws IOException {
        FileOutputStream fos = new FileOutputStream(bundleZip.normalize().toString());
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = srcPath.resolve(rootDirectory).toFile();

        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();

        return bundleZip;
    }

    public static Path zipPackage(final Path bundleZip, final Path srcPath, final String rootDirectory, final String srcZipFileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(bundleZip.normalize().toString());
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = srcPath.resolve(rootDirectory).toFile();

        zipFile(fileToZip, srcZipFileName, zipOut);
        zipOut.close();
        fos.close();

        return bundleZip;
    }

    public static void zipFile(final File fileToZip, final String fileName, final ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void unzipPackage(Path src, Path dest) throws IOException {
        Files.createDirectories(dest);
        File destDir = dest.toFile();
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src.toFile()));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static void copy(String sourceFileName, String sourceDirectoryPath, Path targetDirectoryPath) throws IOException {

        org.springframework.core.io.Resource sourcePath = new ClassPathResource("templates/" + sourceDirectoryPath + "/" + sourceFileName);
        Files.copy(sourcePath.getFile().toPath(), targetDirectoryPath.resolve(sourceFileName), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copy(String sourceFileName, String sourceDirectoryPath, Path targetDirectoryPath, Class clazz) throws IOException {

        InputStream sourceInputStream = clazz.getClassLoader().getResourceAsStream("templates/" + sourceDirectoryPath + "/" + sourceFileName);

        Files.copy(sourceInputStream, targetDirectoryPath.resolve(sourceFileName), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copy(String sourceFileName, String sourceDirectoryPath, Path targetDirectoryPath, String targetFileName, Class clazz) throws IOException {

        InputStream sourceInputStream = clazz.getClassLoader().getResourceAsStream("templates/" + sourceDirectoryPath + "/" + sourceFileName);

        Files.copy(sourceInputStream, targetDirectoryPath.resolve(targetFileName), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Load the test file content as a <Code>String</Code>
     *
     * @param configFilePrefixe    <Code>String</Code> the path under which we load resources. e.g. "src/test/resources/data/"
     * @param testResourceFileName <Code>String</Code> resource test qualified name. e.g. "LocalTargetConnection.xml"
     * @return the content of the giving file located under test/resource folder
     * @throws IOException if any IO problem occur
     */
    public static String readTestResource(String configFilePrefixe, String testResourceFileName) throws IOException {
        Path expectedPath = Paths.get(configFilePrefixe + testResourceFileName);
        return Files.readString(expectedPath);
    }
}
