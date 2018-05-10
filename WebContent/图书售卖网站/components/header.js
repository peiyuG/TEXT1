var CookieUtil = {

    get: function (name){
        var cookieName = encodeURIComponent(name) + "=",
            cookieStart = document.cookie.indexOf(cookieName),
            cookieValue = null,
            cookieEnd;
            
        if (cookieStart > -1){
            cookieEnd = document.cookie.indexOf(";", cookieStart);
            if (cookieEnd == -1){
                cookieEnd = document.cookie.length;
            }
            cookieValue = decodeURIComponent(document.cookie.substring(cookieStart + cookieName.length, cookieEnd));
        } 

        return cookieValue;
    },
    
    set: function (name, value, expires, path, domain, secure) {
        var cookieText = encodeURIComponent(name) + "=" + encodeURIComponent(value);
    
        if (expires instanceof Date) {
            cookieText += "; expires=" + expires.toGMTString();
        }
    
        if (path) {
            cookieText += "; path=" + path;
        }
    
        if (domain) {
            cookieText += "; domain=" + domain;
        }
    
        if (secure) {
            cookieText += "; secure";
        }
    
        document.cookie = cookieText;
    },
    
    unset: function (name, path, domain, secure){
        this.set(name, "", new Date(0), path, domain, secure);
    }

};


(function header() {
    let headerHTML = `<nav class="navbar navbar-inverse">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <a href="javascript:;" class="navbar-brand">网上书城</a>
                            </div>
                            <div class="navbar-collapse navbar-left">
                                <ul class="nav navbar-nav">
                                    <li><a href="index.html">商城首页</a></li>
                                    <li id="person"><a href="user_management.html">个人中心</a></li>
                                    <li id="shop"><a href="shop.html">店铺信息</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>`;
    $('body').prepend(headerHTML);
    $('body').append('<div class="cover-layer"></div>')
    // console.log(CookieUtil.get('status'))
    if(CookieUtil.get('status') == 1){
        $('#shop').remove();
    }else if(CookieUtil.get('status') == 2){
        $('#person').remove();
    }
})()
