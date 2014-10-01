/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addPerson() {
    var data = "{ firstName: " + $("#fname").val() + ", lastName: " + $("#lname").val() +
            ", mail: " + $("#mail").val() + ", phone: " + $("#phone").val() + "}";

    alert(data);

    $.ajax({
        url: "http://localhost:8080/person",
        type: "POST",
        data: data
    }).done(showAllPersons());


}

function showAllPersons() {

    $.ajax({
        url: "http://localhost:8080/person",
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        },
        dataType: 'json'

    }).done(function (data) {
        var res = createTable(data);
        $("#personTable").html(res);
    });

}