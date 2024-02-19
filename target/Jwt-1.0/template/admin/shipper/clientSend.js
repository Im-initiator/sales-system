const element = $('#errorSystem');

function update(status){
    var ob = {};
    ob["status"] = status;
    $.ajax({
        url:apiShipper,
        type:'PUT',
        contentType:'application/json',
        data:JSON.stringify(ob),
        success: function(result){
            element.removeClass().addClass("alert alert-success");
            element.text("Cập nhật trạng thái thành công");
            element.show();
            setTimeout(function() {
                element.hide(); // Ẩn thông báo sau 3 giây
                element.removeClass("alert alert-success");
                window.location.href =link;
            }, 2000);
        },
        error: function(xhr,status,error){
            var textbody = xhr.responseText; //lấy nội dung phản hồi từ body
            element.removeClass().addClass("alert alert-danger");
            if(!textbody.trim().startsWith("Error")){
                $('#errorSystem').text("Lỗi hệ thống");
            }else{
                $('#errorSystem').text(textbody);
            }
            element.show();
            setTimeout(function() {
                element.hide(); // Ẩn thông báo sau 3 giây
                element.removeClass("alert alert-danger");
            }, 2000);
        }

    })
}

$('#delivery-success').click(function (e){
    e.preventDefault();
    update(5);
})
$('#delivery-fail').click(function (e){
    e.preventDefault();
    update(0);
})