document.addEventListener('DOMContentLoaded', function () {


    let currentLoc = String(window.location);
    let newLoc = currentLoc.substring(currentLoc.lastIndexOf('/') + 1);

    fetch('/story/' + newLoc)
        .then(response => response.json())
        .then(data => data.forEach(item => {
        let card = document.createElement('tr');
        card.innerHTML = `
                        <th>${item.date}</th>
                        <th>${item.boardGame.name}</th>
                        <th>${item.score}</th>
                   `;
        document.querySelector('#winStory').appendChild(card);
        })).catch(err => console.log(err));

    $("#name").dblclick(function () {
        let originalValue;
        originalValue = $(this).text();
        $(this).text("");
        $("<input id='newName' name='textfield' type='text' class='form-control-sm' value='value'> <button id='changeName' class='btn btn-success btn-sm'>&#128190;</button>").appendTo(this);
        let formValue = document.getElementById('newName');
        formValue.value = originalValue;
        $("#changeName").click(function (){
            $("#changeName").html("&#10004;");
        });
    });

    $("#surname").dblclick(function () {
        let originalValue;
        originalValue = $(this).text();
        $(this).text("");
        $("<input id='newSurname' name='textfield' type='text' class='form-control-sm' value='value'> <button id='changeSurname' class='btn btn-success btn-sm'>&#128190;</button>").appendTo(this);
        let formValue = document.getElementById('newSurname');
        formValue.value = originalValue;
        $("#changeSurname").click(function (){
            $("#changeSurname").html("&#10004;");
        });
    });

    $("#password").dblclick(function () {
        let originalValue;
        originalValue = $(this).text();
        $(this).text("");
        $("<input id='newPassword' name='textfield' type='text' class='form-control-sm' value='value'> <button id='changePassword' class='btn btn-success btn-sm'>&#128190;</button>").appendTo(this);
        let formValue = document.getElementById('newPassword');
        formValue.value = originalValue;
        $("#changePassword").click(function (){
            $("#changePassword").html("&#10004;");

        });
    });

    $("#phoneNumber").dblclick(function () {
        let originalValue;
        originalValue = $(this).text();
        $(this).text("");
        $("<input id='newNumber' name='textfield' type='text' class='form-control-sm' value='value'> <button id='changeNumber' class='btn btn-success btn-sm'>&#128190;</button>").appendTo(this);
        let formValue = document.getElementById('newNumber');
        formValue.value = originalValue;
        $("#changeNumber").click(function (){
            $("#changeNumber").html("&#10004;");
        });
    });

    $("#emailAddress").dblclick(function () {
        let originalValue;
        originalValue = $(this).text();
        $(this).text("");
        $("<input id='newEmail' name='textfield' type='text' class='form-control-sm' value='value'> <button id='changeEmail' class='btn btn-success btn-sm'>&#128190;</button>").appendTo(this);
        let formValue = document.getElementById('newEmail');
        formValue.value = originalValue;
        $("#changeEmail").click(function (){
            $("#changeEmail").html("&#10004;");
        });
    });


    $(document).ready(function () {

        fetch('/players/4page/' + newLoc)
            .then(response => {
                if (response.status == 200)
                    return response.json()
                else {
                    const error = 'Пользователь не найден!'
                    $('body').css("background-image", "none");
                    document.getElementById('body').innerHTML = error
                }
            })
            .then(data => {
                $("#name").html(data.name);
                $("#surname").html(data.surname);
                $("#login").html(data.login);
                $("#emailAddress").html(data.emailAddress);
                $("#phoneNumber").html(data.phoneNumber);
            }).catch(err => console.log(err));

    });
});

// https://pointofboardgames.herokuapp.com/players/4page/