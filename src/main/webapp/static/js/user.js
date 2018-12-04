$(document).ready(function(){
    findAllUser();
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
            var users = $.parseJSON(data);
            var element = "";
            $.each(users,function (n,user) {
                element += "<tr><td>" + (n + 1) + "</td><td>" + user.name + "</td>" +
                    "<td>" + user.jobs + "</td><td>" + user.phone + "</td>" +
                    "<td><input type='button' class='btn btn-info' data-toggle='modal' data-target='#myModal' onclick='updateModal(" + user.id + ")' value='修改' />" +
                    "<input type='button' class='btn btn-danger'" + " onclick='deleteUser(" + user.id + ")' value='删除' /></td></tr>"
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

function deleteUser(id) {
    if (confirm("你确定删除这个用户吗？")) {
        $.ajax({
            url:"/deleteUser",
            type:"post",
            data:JSON.stringify({"id":id}),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success:function (data) {
                if (data == "OK") {
                    alert("用户删除成功！！！");
                    window.location.reload();
                } else {
                    alert("客户删除失败！！！");
                }
            }
        });
    }
}

function updateUser(id) {
    var name = $("#name").val();
    var jobs = $("#jobs").val();
    var phone = $("#phone").val();
    $.ajax({
        url:"/updateUser",
        type:"post",
        data:JSON.stringify({"id":id,"name":name,"jobs":jobs,"phone":phone}),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function (data) {
            if (data == "OK") {
                alert("用户修改成功！！！");
                window.location.reload();
            }else {
                alert("客户修改失败！！！");
            }
        }
    });
}

function selectMethod(id) {
    var name = $("#myModalLabel").html();
    if (name == "修改用户"){
        updateUser(id);
    } else {
        addUser();
    }
}

function updateModal(id) {
    $("#myModalLabel").html("修改用户");
    $(".modal-footer").html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearUser()">关闭</button><button type="button" class="btn btn-primary" data-dismiss="modal" onclick="selectMethod('+ id +')" >提交更改</button>');
    $.ajax({
        url:"/findUserById",
        type:"post",
        data:JSON.stringify({"id":id}),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(user){
            $("#name").val(user.name);
            $("#jobs").val(user.jobs);
            $("#phone").val(user.phone);
        }
    });
}

function clearUser() {
    $("#name").val("");
    $("#jobs").val("");
    $("#phone").val("");
}