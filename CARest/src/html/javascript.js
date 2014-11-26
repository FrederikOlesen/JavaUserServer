/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addPerson() {
    //Method to add a new person. Is used on the "Add new Person" button in the index.html.
    var newPerson = "{ firstName: " + $("#fname").val() + ", lastName: " + $("#lname").val() +
            ", mail: " + $("#mail").val() + ", phone: " + $("#phone").val() + "}";
    $.ajax({
        url: "http://127.0.0.1:8080/person",
        type: "post",
        data: newPerson
    })
}

function deletePerson() {
    var ID = $("#pID").val();

    $.ajax({
        url: "http://127.0.0.1:8080/person/" + ID,
        type: "DELETE"
    })

}


function addRoletoPerson() {

    var dataString = "";

    var personUsername = $("#role").val();
    var personPassword = $("#pID").val();

    $.ajax({
        url: "http://127.0.0.1:8080/login/" + personUsername + "&" + personPassword,
        type: "GET",
        data: dataString

    })

}

function fetchAll() {
    $.ajax({
        url: "../person",
        type: "GET",
        dataType: 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    }).done(function (persons) {
        var options = "";
        persons.forEach(function (person) {
            options += "<option id=" + person.id + ">" + person.firstName + ", " + person.lastName + ", " + person.mail + ", " + person.phone + "</option>";
        });
        $("#persons").html(options);
    });
}