<html>
<head>
    <title>Spring 3.0 MVC Series: Hello World - green tea.com</title>
</head>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script>
    var base=getRootPath();
    function changeLanguage(language) {
        $.ajax({
            type: "POST",
            url: "changelanguage",
            data: "lang=" + language,
            dataType: "json",
            async: true,
            error: function (data, error) {
                alert("change lang error!");
            },
            success: function (data) {
                window.location.reload();
            }
        });
    }

    function getRootPath(){
        var strFullPath=window.document.location.href;
        var strPath=window.document.location.pathname;
        var pos=strFullPath.indexOf(strPath);
        var prePath=strFullPath.substring(0,pos);
        var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
        return(prePath+postPath);
    }
    $(function () {
        // do something
        var lang = getCookie("clientlanguage");

        var src=base+(lang == null ? "/assets/js/validation.strings.en.js" : "/assets/js/validation.strings." + lang + ".js");
        //document.body.appendChild(script);
        $.getScript(src,function(){
            console.log("加载完毕");
            $("#jsLang").html(messageStrings.my_key1.fillArgs("water","melon"));
        })

    });

    /*
         * var str0 = "{0} must smaller than {1}"
         * var str1 = str0.fillArgs("apple", "watermelon");
         * srt1 equals to "apple must smaller than watermelon"
         */
    String.prototype.fillArgs = function() {
        var formated = this;
        for (var i = 0; i < arguments.length; i++) {
            var param = "\{" + i + "\}";
            formated = formated.replace(param, arguments[i])
        }
        return formated;
    }

    function getCookie(name) {

        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));

        if (arr != null) {
            return unescape(arr[2]);
        } else {
            return null;
        }

    }
</script>
<body>

<span><a href="javascript:void(0)" onclick="changeLanguage('en')">EN</a></span>
|

<span><a href="javascript:void(0)" onclick="changeLanguage('zh')">zh</a></span>
<@spring.message "contact.mytestkey"/>
<div id="jsLang"></div>
</body>
</html>