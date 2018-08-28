/**
 * Created by guoshuai on 2018/8/28 0028.
 */
$(function () {
    $("#connect_form_submit").click(function () {
        $.ajax({
            url: "/connect/index",
            type: "post",
            data: $("#connect_form").serialize(),
            success: function (data) {
                console.log(data);
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
});