<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Spring 3.0 MVC Series: Hello World - green tea.com</title>
</head>
<script>
    var easeyeI18nLang="ch";
    var easeyeI18n = {};
    easeyeI18n["en"] = {
        "contactName": "contact name",
        "userName": "user name"
    };
    easeyeI18n["ch"] = {
        "contactName": "联系人姓名",
        "userName": "用户帐号"
    };


    function getEaseyeI18nText(langKey) {
        return easeyeI18n[easeyeI18nLang][langKey];
    }
    function switchLanguage(language) {
        if (window.location.href.indexOf(".do") == -1) {
            open("/changelanguage?language=" + language + "&originalUrl=" + encodeURI(window.location.href), "_self");
        } else {
            if (window.location.href.indexOf(".do?") == -1) {
                open("?language=" + language, "_self");
            } else {
                open("&language=" + language, "_self");
            }
        }
    }

</script>
<body>


<font color="red">
    mytestkey: <spring:message code="contact.mytestkey"/>
    Current Locale : ${pageContext.response.locale}
</font>

<a href="${ctx}/changelanguage?language=en">EN</a>
<a href="${ctx}/changelanguage?language=zh_CN">CN</a>
<%--
<jsp:forward page="/contact">

    <jsp:param name="userName" value="Jason.D"/>

</jsp:forward>--%>


<%--<jsp:forward page="contacts" />--%>
${message}
</body>
</html>
