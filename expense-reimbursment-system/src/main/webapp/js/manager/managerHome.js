window.onload = function(){
    document.getElementById("header").innerHTML = new Util().getHeader("MANAGER");
    document.getElementById("footer").innerHTML = new Util().getFooter();   
}