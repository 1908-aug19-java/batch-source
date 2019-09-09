window.onload = function(){
    document.getElementById("header").innerHTML = new Util().getHeader("EMPLOYEE");
    document.getElementById("footer").innerHTML = new Util().getFooter();
}