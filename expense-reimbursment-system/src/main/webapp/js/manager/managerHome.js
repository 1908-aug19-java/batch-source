window.onload = function(){
    document.head.insertAdjacentHTML("beforeend", new Util().getheaderLinks());  
    document.getElementById("header").innerHTML = new Util().getHeader("MANAGER");
    document.getElementById("footer").innerHTML = new Util().getFooter();   
}