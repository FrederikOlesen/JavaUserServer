/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addPerson() {

    var newPerson = "{ firstName: " + $("#fname").val() + ", lastName: " + $("#lname").val() +
            ", mail: " + $("#mail").val() + ", phone: " + $("#phone").val() + "}";
    $.ajax({
        url: "http://127.0.0.1:8080/person",
        type: "post",
        data: newPerson
    })
}

function deletePerson() {
    var ID = $("#id1").val();

    $.ajax({
        url: "http://127.0.0.1:8080/person/" + ID,
        type: "DELETE"
    })

}

function addRolePerson() {
    var ID = $("#id1").val();

    var roleName = "Student";

    $.ajax({
        url: "http://127.0.0.1:8080/person/" + ID,
        type: "PUT",
        data: roleName
    })
}

function addRole() {

    var roleName = $("#role").val();
    var personId = $("#id1").val();
    var data = "";

    if (roleName.toLowerCase() === "student") {
        data += "{semester: 3semester, roleName: Student}";
    } else if (roleName.toLowerCase() === "teacher") {
        data += "{degree: uddannet, roleName: Teacher}";
    } else if (roleName.toLowerCase() === "assistantteacher") {
        data += "{degree: uddannet, roleName: Assistantteacher}";
    }

    $.ajax({
        url: "http://127.0.0.1:8080/person/" + personId,
        type: "PUT",
        dataType: "json",
        data: data
    })
}