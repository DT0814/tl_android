function fsubmit() {
    //var form=document.getElementById("form1");
    var fd =new FormData($("#form1")[0]);
    $.ajax({
        url: "http://localhost:8080/uploadImage",
        type: "POST",
        data: fd,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function(response,status,xhr){
            //console.log(xhr);
            //var json=$.parseJSON(response);
            // var result = '';
            // result +="个人信息：<br/>name:"+json['name']+"<br/>gender:"+json['gender']+"<br/>number:"+json['number'];
            // result += '<br/>头像：<img src="' + json['photo'] + '" height="100" style="border-radius: 50%;" />';
            // $('#result').html(result);
        }
    });
    return false;
}