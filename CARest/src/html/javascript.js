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
function addRoletoPerson() {

    var dataString = "";

    var roleName = $("#role").val();
    var personId = $("#pID").val();


    if (roleName.toLowerCase() === "student") {
        dataString += "{semester: 3semester, roleName: Student}";
    } else if (roleName.toLowerCase() === "assistantteacher") {
        dataString += "{degree: uddannet, roleName: Assistantteacher}";
    } else if (roleName.toLowerCase() === "teacher") {
        dataString += "{degree: uddannet, roleName: Teacher}";
    }
    $.ajax({
        url: "http://127.0.0.1:8080/person/" + personId,
        type: "PUT",
        data: dataString

    })

}