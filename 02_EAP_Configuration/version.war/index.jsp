<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.io.*,java.net.*,java.util.jar.*,javax.xml.parsers.*,org.w3c.dom.*"
    %>
    
<%
//Path to the jboss-as-ee.jar module
String jarPath = "../modules/system/layers/base/org/jboss/as/ee/main/";
String jarPath2 = "modules/system/layers/base/org/jboss/as/ee/main/";
String correctJarPath="";
String moduleFile = jarPath + "module.xml";
correctJarPath = jarPath;
String eapVersion = null;
try {
	//The version number of EAP is found inside the JAR file defined in module.xml of the jboss/as/ee module
	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	Document doc = null;
    try {
		doc = docBuilder.parse (new File(moduleFile));
	}catch(FileNotFoundException f) {
		//Try an alternate path
		moduleFile = jarPath2 + "module.xml";
		correctJarPath = jarPath2;
		doc = docBuilder.parse (new File(moduleFile));
	}
    NodeList resourceRootList = doc.getElementsByTagName("resource-root");
    Node resourceRootNode = resourceRootList.item(0);
    String jarFilename = correctJarPath + resourceRootNode.getAttributes().getNamedItem("path").getNodeValue();
	JarFile jarFile = new JarFile(jarFilename);
	Manifest manifest = jarFile.getManifest();
    Attributes attrs = (Attributes) manifest.getMainAttributes();
    Attributes.Name attrName = new Attributes.Name("JBossEAP-Release-Version");
    eapVersion = attrs.getValue(attrName);
    //If it's null, it could be a JBoss AS version
    if(eapVersion == null || eapVersion.equals("")) {
	    attrName = new Attributes.Name("JBossAS-Release-Version");
    	eapVersion = attrs.getValue(attrName);
    }
}catch(Exception e) {
	e.printStackTrace();
	eapVersion = "JBoss Version not found";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Version App</title>
</head>
<body>
<h1>EAP 7 Version App</h1>
<ul>
	<li>This is a simple web app to demonstrate and verify the successful deployment of a WAR.</li>
	<li>This app also displays the version of JBoss EAP that it is currently deployed on, which can be useful for verifying a
		specific EAP deployment.</li>
</ul>
<br/>
<p>You are running the <b>1.0</b> version of this application.</p>
<p>This app is currently deployed on the following version of JBoss EAP: <b><%= eapVersion %></b></p>
<p>Server name: <b><%= request.getServerName() %></b></p>
<p>Server port: <b><%= request.getServerPort() %></b></p>
</body>
</html>