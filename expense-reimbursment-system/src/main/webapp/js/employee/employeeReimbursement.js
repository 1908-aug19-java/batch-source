window.onload = function(){
    document.getElementById("header").innerHTML = new Util().getHeader("employee");
    document.getElementById("footer").innerHTML = new Util().getFooter();
}