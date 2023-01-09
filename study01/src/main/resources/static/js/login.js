//조건을 만족할 때만 클릭시 버튼색 변경
const btn = document.querySelector(".btnLogin");
btn.addEventListener("click",function(e){
    const inputId = document.querySelector('#userId').value;
    const inputPw = document.querySelector('#userPw').value;
    if(inputId.length > 5 && inputPw.length > 4){
        alert("환영합니다.")
        document.querySelector(".btnLogin").style.background = "rgb(46, 184, 223)";
        // location.href="main.html";
    }else{
        alert("잘못된 양식입니다.");
        document.querySelector(".btnLogin").style.background= "default";
    }
    return false;
});