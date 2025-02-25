package fr.groupbees.apigeesdk.apigee.policies;

import fr.groupbees.apigeesdk.apigee.policies.extension.FlowCalloutPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.JavaCalloutPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.JavaScriptPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.PythonScriptPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.extensioncallout.ExtensionCalloutPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.messagelogging.MessageLoggingPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.servicecallout.ServiceCalloutPolicy;
import fr.groupbees.apigeesdk.apigee.policies.extension.statisticcollector.StatisticsCollectorPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.accessentity.AccessEntityPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.assignmessage.AssignMessagePolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.extractvariables.ExtractVariablesPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.jsontoxml.JSONtoXMLPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.kvm.KeyValueMapOperationsPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.oasvalidation.OASValidationPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.raisefault.RaiseFaultPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.soapmessagevalidation.SOAPMessageValidationPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.xmltojson.XMLtoJSONPolicy;
import fr.groupbees.apigeesdk.apigee.policies.mediation.xsltransform.XSLTransformPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.JSONThreatProtectionPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.VerifyAPIKeyPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.accesscontrol.AccessControlPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.basicauth.BasicAuthenticationPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.jwx.*;
import fr.groupbees.apigeesdk.apigee.policies.security.ldap.LDAPPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.oauth.*;
import fr.groupbees.apigeesdk.apigee.policies.security.regexpprotection.RegularExpressionProtectionPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.saml.GenerateSAMLAssertionPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.saml.ValidateSAMLAssertionPolicy;
import fr.groupbees.apigeesdk.apigee.policies.security.xmlthreatprotection.XMLThreatProtectionPolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.SpikeArrestPolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache.InvalidateCachePolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache.LookupCachePolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache.PopulateCachePolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.cache.ResponseCachePolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.concurrentratelimit.ConcurrentRateLimitPolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.quota.QuotaPolicy;
import fr.groupbees.apigeesdk.apigee.policies.trafficmanagement.resetquota.ResetQuotaPolicy;

import java.util.HashMap;
import java.util.Map;

public class PolicyFactory {

  public final static Map<String, Class<? extends Policy>> TYPE_CLASS_MAP = new HashMap<>();
  public final static Map<Class<? extends Policy>, String> CLASS_TYPE_MAP = new HashMap<>();

  static {
    TYPE_CLASS_MAP.put("extensioncallout", ExtensionCalloutPolicy.class);
    TYPE_CLASS_MAP.put("messagecogging", MessageLoggingPolicy.class);
    TYPE_CLASS_MAP.put("servicecallout", ServiceCalloutPolicy.class);
    TYPE_CLASS_MAP.put("statisticscollector", StatisticsCollectorPolicy.class);
    TYPE_CLASS_MAP.put("flowcallout", FlowCalloutPolicy.class);
    TYPE_CLASS_MAP.put("javacallout", JavaCalloutPolicy.class);
    TYPE_CLASS_MAP.put("javascript", JavaScriptPolicy.class);
    TYPE_CLASS_MAP.put("pythonscript", PythonScriptPolicy.class);
    TYPE_CLASS_MAP.put("accessentity", AccessEntityPolicy.class);
    TYPE_CLASS_MAP.put("assignmessage", AssignMessagePolicy.class);
    TYPE_CLASS_MAP.put("am", AssignMessagePolicy.class);
    TYPE_CLASS_MAP.put("extractvariables", ExtractVariablesPolicy.class);
    TYPE_CLASS_MAP.put("ev", ExtractVariablesPolicy.class);
    TYPE_CLASS_MAP.put("jsontoxml", JSONtoXMLPolicy.class);
    TYPE_CLASS_MAP.put("json2xml", JSONtoXMLPolicy.class);
    TYPE_CLASS_MAP.put("kvm", KeyValueMapOperationsPolicy.class);
    TYPE_CLASS_MAP.put("keyvaluemapoperations", KeyValueMapOperationsPolicy.class);
    TYPE_CLASS_MAP.put("oasvalidation", OASValidationPolicy.class);
    TYPE_CLASS_MAP.put("raisefault", RaiseFaultPolicy.class);
    TYPE_CLASS_MAP.put("soapmessagevalidation", SOAPMessageValidationPolicy.class);
    TYPE_CLASS_MAP.put("xsltransform", XSLTransformPolicy.class);
    TYPE_CLASS_MAP.put("xslt", XSLTransformPolicy.class);
    TYPE_CLASS_MAP.put("xmltojson", XMLtoJSONPolicy.class);
    TYPE_CLASS_MAP.put("xml2json", XMLtoJSONPolicy.class);
    TYPE_CLASS_MAP.put("accesscontrol", AccessControlPolicy.class);
    TYPE_CLASS_MAP.put("basicauthentication", BasicAuthenticationPolicy.class);
    TYPE_CLASS_MAP.put("basicauth", BasicAuthenticationPolicy.class);
    TYPE_CLASS_MAP.put("decodejws", DecodeJWSPolicy.class);
    TYPE_CLASS_MAP.put("decodejwt", DecodeJWTPolicy.class);
    TYPE_CLASS_MAP.put("generatejws", GenerateJWSPolicy.class);
    TYPE_CLASS_MAP.put("generatejwt", GenerateJWTPolicy.class);
    TYPE_CLASS_MAP.put("verifyjws", VerifyJWSPolicy.class);
    TYPE_CLASS_MAP.put("verifyjwt", VerifyJWTPolicy.class);
    TYPE_CLASS_MAP.put("ldap", LDAPPolicy.class);
    TYPE_CLASS_MAP.put("deleteoauthv1info", DeleteOAuthV1InfoPolicy.class);
    TYPE_CLASS_MAP.put("deleteoauthv2info", DeleteOAuthV2InfoPolicy.class);
    TYPE_CLASS_MAP.put("getoauthv1info", GetOAuthV1InfoPolicy.class);
    TYPE_CLASS_MAP.put("getoauthv2info", GetOAuthV2InfoPolicy.class);
    TYPE_CLASS_MAP.put("oauthv1", OAuthV1Policy.class);
    TYPE_CLASS_MAP.put("oauthv2", OAuthV2Policy.class);
    TYPE_CLASS_MAP.put("setoauthv2info", SetOAuthV2InfoPolicy.class);
    TYPE_CLASS_MAP.put("regularexpressionprotection", RegularExpressionProtectionPolicy.class);
    TYPE_CLASS_MAP.put("regex", RegularExpressionProtectionPolicy.class);
    TYPE_CLASS_MAP.put("generatesamlassertion", GenerateSAMLAssertionPolicy.class);
    TYPE_CLASS_MAP.put("validatesamlassertion", ValidateSAMLAssertionPolicy.class);
    TYPE_CLASS_MAP.put("xmlthreatprotection", XMLThreatProtectionPolicy.class);
    TYPE_CLASS_MAP.put("jsonthreatprotection", JSONThreatProtectionPolicy.class);
    TYPE_CLASS_MAP.put("verifyapikey", VerifyAPIKeyPolicy.class);
    TYPE_CLASS_MAP.put("invalidatecache", InvalidateCachePolicy.class);
    TYPE_CLASS_MAP.put("lookupcache", LookupCachePolicy.class);
    TYPE_CLASS_MAP.put("populatecache", PopulateCachePolicy.class);
    TYPE_CLASS_MAP.put("responsecache", ResponseCachePolicy.class);
    TYPE_CLASS_MAP.put("concurrentratelimit", ConcurrentRateLimitPolicy.class);
    TYPE_CLASS_MAP.put("quota", QuotaPolicy.class);
    TYPE_CLASS_MAP.put("resetquota", ResetQuotaPolicy.class);
    TYPE_CLASS_MAP.put("spikearrest", SpikeArrestPolicy.class);

    CLASS_TYPE_MAP.put(ExtensionCalloutPolicy.class, "extensioncallout");
    CLASS_TYPE_MAP.put(MessageLoggingPolicy.class, "messagecogging");
    CLASS_TYPE_MAP.put(ServiceCalloutPolicy.class, "servicecallout");
    CLASS_TYPE_MAP.put(StatisticsCollectorPolicy.class, "statisticscollector");
    CLASS_TYPE_MAP.put(FlowCalloutPolicy.class, "flowcallout");
    CLASS_TYPE_MAP.put(JavaCalloutPolicy.class, "javacallout");
    CLASS_TYPE_MAP.put(JavaScriptPolicy.class, "javascript");
    CLASS_TYPE_MAP.put(PythonScriptPolicy.class, "pythonscript");
    CLASS_TYPE_MAP.put(AccessEntityPolicy.class, "accessentity");
    CLASS_TYPE_MAP.put(AssignMessagePolicy.class, "assignmessage");
    CLASS_TYPE_MAP.put(ExtractVariablesPolicy.class, "extractvariables");
    CLASS_TYPE_MAP.put(JSONtoXMLPolicy.class, "jsontoxml");
    CLASS_TYPE_MAP.put(KeyValueMapOperationsPolicy.class, "keyvaluemapoperations");
    CLASS_TYPE_MAP.put(OASValidationPolicy.class, "oasvalidation");
    CLASS_TYPE_MAP.put(RaiseFaultPolicy.class, "raisefault");
    CLASS_TYPE_MAP.put(SOAPMessageValidationPolicy.class, "soapmessagevalidation");
    CLASS_TYPE_MAP.put(XSLTransformPolicy.class, "xsltransform");
    CLASS_TYPE_MAP.put(XMLtoJSONPolicy.class, "xmltojson");
    CLASS_TYPE_MAP.put(AccessControlPolicy.class, "accesscontrol");
    CLASS_TYPE_MAP.put(BasicAuthenticationPolicy.class, "basicauthentication");
    CLASS_TYPE_MAP.put(DecodeJWSPolicy.class, "decodejws");
    CLASS_TYPE_MAP.put(DecodeJWTPolicy.class, "decodejwt");
    CLASS_TYPE_MAP.put(GenerateJWSPolicy.class, "generatejws");
    CLASS_TYPE_MAP.put(GenerateJWTPolicy.class, "generatejwt");
    CLASS_TYPE_MAP.put(VerifyJWSPolicy.class, "verifyjws");
    CLASS_TYPE_MAP.put(VerifyJWTPolicy.class, "verifyjwt");
    CLASS_TYPE_MAP.put(LDAPPolicy.class, "ldap");
    CLASS_TYPE_MAP.put(DeleteOAuthV1InfoPolicy.class, "deleteoauthv1info");
    CLASS_TYPE_MAP.put(DeleteOAuthV2InfoPolicy.class, "deleteoauthv2info");
    CLASS_TYPE_MAP.put(GetOAuthV1InfoPolicy.class, "getoauthv1info");
    CLASS_TYPE_MAP.put(GetOAuthV2InfoPolicy.class, "getoauthv2info");
    CLASS_TYPE_MAP.put(OAuthV1Policy.class, "oauthv1");
    CLASS_TYPE_MAP.put(OAuthV2Policy.class, "oauthv2");
    CLASS_TYPE_MAP.put(SetOAuthV2InfoPolicy.class, "setoauthv2info");
    CLASS_TYPE_MAP.put(RegularExpressionProtectionPolicy.class, "regularexpressionprotection");
    CLASS_TYPE_MAP.put(GenerateSAMLAssertionPolicy.class, "generatesamlassertion");
    CLASS_TYPE_MAP.put(ValidateSAMLAssertionPolicy.class, "validatesamlassertion");
    CLASS_TYPE_MAP.put(XMLThreatProtectionPolicy.class, "xmlthreatprotection");
    CLASS_TYPE_MAP.put(JSONThreatProtectionPolicy.class, "jsonthreatprotection");
    CLASS_TYPE_MAP.put(VerifyAPIKeyPolicy.class, "verifyapikey");
    CLASS_TYPE_MAP.put(InvalidateCachePolicy.class, "invalidatecache");
    CLASS_TYPE_MAP.put(LookupCachePolicy.class, "lookupcache");
    CLASS_TYPE_MAP.put(PopulateCachePolicy.class, "populatecache");
    CLASS_TYPE_MAP.put(ResponseCachePolicy.class, "responsecache");
    CLASS_TYPE_MAP.put(ConcurrentRateLimitPolicy.class, "concurrentratelimit");
    CLASS_TYPE_MAP.put(QuotaPolicy.class, "quota");
    CLASS_TYPE_MAP.put(ResetQuotaPolicy.class, "resetquota");
    CLASS_TYPE_MAP.put(SpikeArrestPolicy.class, "spikearrest");
  }

  public static Class<? extends Policy> policyTypeToClass(final PolicyType policyType) {
    if (TYPE_CLASS_MAP.containsKey(policyType.getPolicyType().toLowerCase())) {
      return TYPE_CLASS_MAP.get(policyType.getPolicyType().toLowerCase());
    }

    throw new IllegalArgumentException(policyType.getPolicyType() + " is not a valid policy type");
  }

  public static String classToPolicyType(final Class<? extends Policy> clazz) {
    if (CLASS_TYPE_MAP.containsKey(clazz)) {
      return CLASS_TYPE_MAP.get(clazz);
    }

    throw new IllegalArgumentException(clazz.getName() + " is not a valid policy class");
  }

}
