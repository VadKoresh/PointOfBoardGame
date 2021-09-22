document.addEventListener('DOMContentLoaded', function () {

    var playersAr = [];
    var boardGameAr = [];

    playersArray();
    boardGameArray();

    $('#takescore').click(function () {
        $('#scoreform').modal('show');
    });

    $(document).ready(function () {

        var k = $('input').size() + 1;

        $('#add').click(function () {
            let llineNull = document.createElement('br');
            document.querySelector('.inputs').appendChild(llineNull);

            let select = document.createElement('select');
            select.onchange = "alert(this.value)";
            select.classList.add('playerID');
            for (var i = 0; i < playersAr.length; i++) {
                var opt = playersAr[i];
                var el = document.createElement('option');
                el.textContent = opt.name + ' ' + opt.surname;
                el.value = opt.id;
                select.appendChild(el);
            }
            document.querySelector('.inputs').appendChild(select);

            // let selectPoint = document.createElement('select');
            // select.classList.add('playerScores');
            // for (var i = 0; i < document.g; i++) {
            //
            // }

            $('<input type="text" class="playerScores" placeholder="Очки игрока" />').appendTo('.inputs');
            k++;
        });

        $('#remove').click(function () {
            if (k > 1) {
                $('.playerID:last').remove();
                $('.playerScores:last').remove();
                k--;
            }
        });


        $('.submit').click(function () {

            var requestBody = [];

            var gameID = $('#choosedGame').val();

            var players = [];
            $.each($('.playerID'), function () {
                players.push($(this).val());
            });

            var scores = [];
            $.each($('.playerScores'), function () {
                scores.push($(this).val());
            });


            if (players.length == 0) {
                answers = "Игра, игроки, баллы не выбраны";
            }

            for (var a = 0; a < players.length; a++) {
                var requestObj = {
                    player: {
                        id: 0,
                    },
                    boardGameEntity: {
                        id: 0,
                    },
                    score: 0
                };
                requestObj.player.id = players[a];
                requestObj.boardGameEntity.id = gameID;
                requestObj.score = scores[a];
                requestBody.push(requestObj);
            }

            function hello() {
                console.log(requestObj.score);
                var jsonArray = JSON.stringify(requestBody);
                console.log(jsonArray);
                let request = new XMLHttpRequest();
                request.open('POST', '/story/');
                request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                request.send(jsonArray);//сюда мы можем боди
                console.log("вроде как");
            }

            hello();

        });

    });

    function playersArray() {
        let request = new XMLHttpRequest();
        request.open('GET', '/players/');
        request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        request.send();//сюда мы можем боди

        request.addEventListener('readystatechange', function () {
            if (request.readyState == 4 && request.status == 200) {
                let data = JSON.parse(request.response);
                data.forEach(item => {
                    playersAr.push(item)
                });
            } else {
                console.error("Что-то пошло не такподтяжке игроков");
            }
        });
    }

    function boardGameArray() {
        let request = new XMLHttpRequest();
        request.open('GET', '/boardgame/listbgwlp');
        request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        request.send();//сюда мы можем боди

        request.addEventListener('readystatechange', function () {
            if (request.readyState == 4 && request.status == 200) {
                let data = JSON.parse(request.response);
                data.forEach(item => {
                    boardGameAr.push(item)
                });
                let select = document.createElement('select');
                select.id = "choosedGame";
                for (var i = 0; i < boardGameAr.length; i++) {
                    var opt = boardGameAr[i];
                    var el = document.createElement('option');
                    el.textContent = opt.name;
                    el.value = opt.id;
                    select.appendChild(el);
                }
                document.querySelector('.fieldsetGame').appendChild(select);
            } else {
                console.error("Что-то пошло не так в подтяжке игры");
            }
        });

    }

});


