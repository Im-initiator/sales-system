const element = $('#errorSystem');
$("#status option").each(function() {
    if ($(this).val() === status) {
        $(this).prop("selected", true);
    }
});
$('#update-form').click(function (e){
    e.preventDefault();
    var formData = $('#form-submit').serializeArray();
    var data = {};
    $.each(formData,function(i,v){
        data[""+v.name+""] = v.value;
    });
    console.log(formData);
    $.ajax({
        url:api,
        type:'PUT',
        contentType:'application/json',
        data:JSON.stringify(data),
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
})