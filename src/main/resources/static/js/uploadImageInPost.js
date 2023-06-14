$(document).ready(function () {
    $("#submit").on("click", function () {
        $("#submit").prop("disabled", true);
        var name = $("#header").val();
        var file = $("#previewImage").val();
        var file2 = $("#image").val();
        var description = $("#description").val();
        var form = $("#form").serialize();
        var data = new FormData($("#form")[0]);
        data.append('name', name);
        data.append('description', description);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            url: "/news/create",
            processData: false,
            contentType: false,
            cache: false,
            success: function (data, statusText, xhr) {
                console.log(xhr.status);
                if (statusText === "success") {
                    $("#form")[0].reset();
                    $('#success').css('display', 'block');
                    $("#error").text("");
                    $("#success").html("Image inserted successfully.");
                    $('#success').delay(2000).fadeOut('slow');
                    $('#success').location.href = '/home'
                }
            }
        });

    });
});


