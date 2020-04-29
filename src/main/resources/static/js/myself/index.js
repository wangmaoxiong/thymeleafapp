/**
 * images 集合返回当前文档中所有图片(Image)对象的数组。
 * Image 对象文档：https://www.w3school.com.cn/htmldom/dom_obj_image.asp
 */
function showAllImages() {
    var count = 1;
    for (var i = 0; i < window.document.images.length; i++) {
        var image = window.document.images[i];
        //输出如：1 -> http://localhost:8080/images/colorpreset/preset1/preset1.png
        console.log((count++) + " -> " + image.src);
    }
}

/**
 * links 集合返回当前文档中所有 Area 和 Link 对象的数组。
 */
function showAllLinks() {
    var count = 1;
    for (var i = 0; i < window.document.links.length; i++) {
        var link = window.document.links[i];
        //输出如：85 -> href= http://localhost:8080/#, text=View Campaign
        console.log((count++) + " -> href= " + link.href + ", text=" + link.text);
    }
}

/**
 * document domain 属性可返回下载当前文档的服务器域名。
 * lastModified 属性可返回文档最后被修改的日期和时间，该值来自于 HTTP 响应 Last-Modified 属性，是由 Web 服务器发送的可选项
 * title 属性可返回当前文档的标题（ HTML title 元素中的文本）。
 * URL 属性可返回当前文档的 URL,该属性的值与包含文档的 Window 的 location.href 属性相同。
 * 不过，在 URL 重定向发生的时候，这个 URL 属性保存了文档的实际 URL，而 location.href 保存了请求的 URL。
 */
function docCookie() {
    var domain = document.domain;//如 localhost、127.0.0.1、www.bai.com
    var lastModified = document.lastModified;//如 04/24/2020 22:53:44
    var title = document.title;
    var url = document.URL;//如 http://127.0.0.1:8080/index.html
    console.log(domain + "," + lastModified + "," + title);
    console.log(url);
}

/**
 * referrer 属性可返回载入当前文档的文档的 URL.
 * 如果当前文档不是通过超级链接访问的，则为空字符串。这个属性允许客户端 JavaScript 访问 HTTP 引用头部。
 * 1、比如从 a 页面跳转到 b 页面，则 b 页面的 document.referrer 返回的是 a 页面的地址
 * 2、比如 h1 页面中嵌套了 h2 页面，从 h2 页面跳转到 h3 页面，则h3中的 document.referrer 返回 h2 页面的地址.
 * 所以 document.referrer 也可以用于做返回操作.
 * 3、注意如果在 b页面、h3页面做了类似 window.location.reload() 操作，则 document.referrer 的值会变为自己页面的地址.
 */
function docReferrer() {
    var referrer = document.referrer;
    alert(referrer);
    if ("" != referrer) {
        window.location.href = document.referrer;
    }
}

/**
 * 1、document getElementById(id) 方法可返回对拥有指定 ID 的第一个对象的引用。
 * 2、如果 id 不存在，则返回 null，否则返回选中的 DOM 对象,.
 * 3、可以直接输出此 DOM 对象，查看它的可用属性，如 nodeName 表示标签名称、innerText 表示标签内的文本内容.
 * document.getElementsByName(name):返回带有指定名称的对象的集合。
 *  因为一个文档中的 name 属性可能不唯一，所以 getElementsByName() 方法返回的是元素的数组.
 * document.getElementsByTagName(tagName):返回带有指定标签名的对象的集合，返回元素的顺序是它们在文档中的顺序。
 *  tagName 名称不区分大小写、"*' 星号表示所有标签.
 *
 */
function docGetElement() {
    var showName = document.getElementById("showName");
    console.log("nodeName=" + showName.nodeName + ",innerText=" + showName.innerText);
}

/**
 * document.write(exp1,exp2,exp3,....) 方法可向文档写入 HTML 表达式或 JavaScript 代码。
 * writeln() 方法与 write() 方法作用相同，外加可在每个表达式后写一个换行符。
 * @param msg
 * @param isLineFeed
 */
function docWrite(msg, isLineFeed) {
    if (isLineFeed) {
        document.writeln("<h3>1、" + msg + "</h3>");
    } else {
        document.write("<h3>2、" + msg + "</h3>");
    }
}


$(function () {
    docReferrer();
});