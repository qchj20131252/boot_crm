$(document).ready(function(){
    findAllUser()
});

function findUserById() {
    var id=$("#id").val();
    $.ajax({
        url:"/findUserById",
        type:"post",
        data:JSON.stringify({"id":id}),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(user){
            $("#user_info").html("<tr><td>" + user.id + "</td><td>" + user.name + "</td><td>" + user.jobs + "</td><td>" + user.phone + "</td></tr>");
        }
    });
    alert("提交成功");
}

function findAllUser() {
    $.ajax({
        url: "/findAllUser",
        type: "post",
        success:function (data) {
            console.log(data);
            var users = $.parseJSON(data);
            console.log(users);
            var element = "";
            $.each(users,function (n,user) {
                element += "<tr><td>" + user.id + "</td><td>" + user.name + "</td><td>" + user.jobs + "</td><td>"
                    + user.phone + "</td><td><input type='button' class='btn btn-info' value='修改' /><input type='button' class='btn btn-danger' value='删除' /></td></tr>"
            });
            $("#user_info").html(element);
        }
    });
}

function addUser() {
    var name = $("#name").val();
    var jobs = $("#jobs").val();
    var phone = $("#phone").val();
    $.ajax({
        url:"/addUser",
        type:"post",
        data:JSON.stringify({"name":name,"jobs":jobs,"phone":phone}),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function (data) {
            if (data == "OK") {
                alert("用户创建成功！！！");
                window.location.reload();
            }else {
                alert("客户创建失败！！！");
            }
        }
    });
}