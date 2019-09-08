window.onload = function(){
    document.getElementById("header").innerHTML = new Util().getHeader("manager");
    document.getElementById("footer").innerHTML = new Util().getFooter();
}