
/* User management implemented through datatables, JQuery plugin
 * https://datatables.net/
 * 
 * */


var editor1;
$(document).ready(function () {
    editor1 = new $.fn.dataTable.Editor({
        ajax: {
            create: {
                type: 'POST',

                url: "/api/users", contentType: "application/json",
                dataType: 'json',
                data: function (d) {
                   
                    return JSON.stringify({

                        "userName": $("#DTE_Field_userName").val(),
                        "password": $('#DTE_Field_password').val(),
                        "contactNo": $('#DTE_Field_contactNo').val(),

                    });
                },
                success: function (response) {
                 
                    $('#usersTable').DataTable().ajax.reload();
                },
                error: function (msg) {
                    //alert();
                    $('.DTE_Footer_Content').html("Error:"+msg.responseJSON.localizedMessage);
                    console.log(msg);
                }


            },
            edit: {

                type: 'PUT',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',

                url: "/api/users/" + "_id_",
                data: function (d) {

                   
                    return JSON.stringify({
                        "userName": $("#DTE_Field_userName").val(),
                        "password": $('#DTE_Field_password').val(),
                         "contactNo": $('#DTE_Field_contactNo').val()
                        
                    });

                },
                error: function (msg) {
                      $('.DTE_Footer_Content').html("Error:"+msg.responseJSON.localizedMessage);
                    console.log(msg);
                },
                success: function (msg) {
                    console.log("Success:" + msg.message);
                }

            },
            remove: {
                type: 'DELETE',
                dataType: 'json',
                url: "/api/users/" + "_id_"

            },
            error: function (e) {
                console.log(e);
            }


        },
        table: "#usersTable",
        idSrc: "id",
        fields: [
            {
                label: "User Name",
                name: "userName"
            }, {
                label: "Password",
                name: "password",
                type: "password"



            },{
                label: "Contact No",
                name: "contactNo"
            },

            /*{
                "label": "User Roles:",
                "name": "roles[].id",
                "type": "select",
                "multiple": true,
                options: [
                    {label: "Admin", value: 1},
                    {label: "User", value: 2}]








            }*/
        ], error: function (e) {
            console.log(e);
        }
    });
    editor1.on('postSubmit', function (e, json, data, action) {
        var rowData = $.extend({}, json);

        json.data = [rowData];
        console.log(data);
    });

    var table = $('#usersTable').DataTable({
        "ajax": "/api/users",

        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "columns": [
            {"data": "userName", "defaultContent": ""},
            {"data": "contactNo", "defaultContent": ""}
        ], select: true
    });

    new $.fn.dataTable.Buttons(table, [
        {extend: "create", editor: editor1},
        {extend: "edit", editor: editor1},
        {extend: "remove", editor: editor1}
    ]);

    table.buttons().container()
            .appendTo($('.col-md-6:eq(0)', table.table().container()));
});

