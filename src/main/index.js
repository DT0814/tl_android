function fsubmit() {
    //var form=document.getElementById("form1");
    var fd =new FormData($("#form1")[0]);
    $.ajax({
        url: "http://localhost:8080/uploadImage",
        type: "POST",
        data: fd,
        processData: false,
        contentType: false,
    });
    return false;
}