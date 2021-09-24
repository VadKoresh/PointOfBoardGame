document.addEventListener('DOMContentLoaded', function () {

    /**
     * В данных массивах содержатся объекты Игроки и Настольные игры.
     * В переменноей indexGame содержится индекс Игры к которой мы будем обращаться
     */
    var playersAr = [];
    var boardGameAr = [];
    var indexGame = 0;

    /**
     * playersArray() запускает запрос "Список всех зарегистрированных игроков" при формировании страницы и записывает в массив playersAr.
     * boardGameArray() запускает запрос "Список всех настольныхх игры" при формировании страницы и записывает в массив boardGameAr.
     */
    playersArray();
    boardGameArray();
    init();


    $('#takescore').click(function () {
        $('#scoreform').modal('show');
    });

    $(document).ready(function () {

        var k = $('input').size() + 1;

        /**
         *  divElement - добавляет новый див.
         *  selectPlayer - добавляет селект со списком игроков.
         *  selectScore - добавляет селект с победными очками характерными для выбранной игры.
         */
        $('#add').click(function () {
            let divElement = document.createElement('div');

            let selectPlayer = document.createElement('select');
            selectPlayer.classList.add('playerID');
            for (var i = 0; i < playersAr.length; i++) {
                var opt = playersAr[i];
                var el = document.createElement('option');
                el.textContent = opt.name + ' ' + opt.surname;
                el.value = opt.id;
                selectPlayer.appendChild(el);
            }

            let selectScore = document.createElement('select');
            selectScore.classList.add('playerScores');
            setSelectScore(selectScore);

            divElement.appendChild(selectPlayer);
            divElement.appendChild(selectScore);
            document.querySelector('.inputs').appendChild(divElement);

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

            /**
             * Функция для отправки начисленяи всех баллов.
             */
            function hello() {
                console.log(requestObj.score);
                var jsonArray = JSON.stringify(requestBody);
                console.log(jsonArray);
                let request = new XMLHttpRequest();
                request.open('POST', '/story/');
                request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                request.send(jsonArray);
            }
            hello();

        });

    });

    /**
     * Запрос для получения списка всех игроков.
     */
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
            }
        });
    }

    /**
     * Запрос для получения списка всех игры.
     * В переменной select повешена функция изменения всех селектов по начислению победных очков.
     */
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
                select.onchange = function () {
                    indexGame = select.options.selectedIndex;
                    $.each($('.playerScores'), function () {
                        setSelectScore(this);
                        document.getElementById('fieldPlayerAndScore');
                    });
                }
                select.id = "choosedGame";
                for (var i = 0; i < boardGameAr.length; i++) {
                    var opt = boardGameAr[i];
                    var el = document.createElement('option');
                    el.textContent = opt.name;
                    el.value = opt.id;
                    select.appendChild(el);
                }
                document.querySelector('.fieldsetGame').appendChild(select);
            }
        });
    }

    /**
     * Данная функция преднозначена для изменения/добавляния селектов относительно выбранной игры.
     */
    function setSelectScore(element) {
        if (element.length > 0) {
            element.length = 0;
        }
        for (var i = 0; i < boardGameAr[indexGame].points.length; i++) {
            var opt = boardGameAr[indexGame].points[i];
            var el = document.createElement('option');
            el.textContent = opt;
            el.value = opt;
            element.appendChild(el);
        }
    }

    function init() {
        let request = new XMLHttpRequest();
        request.open('GET', '/winners/');
        request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        request.send();//сюда мы можем боди

        var count = 1;

        request.addEventListener('readystatechange', function () {
            if (request.readyState == 4 && request.status == 200) {
                let data = JSON.parse(request.response);
                data.forEach(item => {
                    let card = document.createElement('tr');

                    card.innerHTML = `
                        <th>${count}</th>
                        <th><a href="/player/page/${item.player.id}">${item.player.name} ${item.player.surname}</a></th>
                        <th>${item.score}</div>
                   `;
                    document.querySelector('.table').appendChild(card);
                    count++;
                });
            }
        });
    }
});


