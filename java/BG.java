package ru.drudenko.sms.testclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * created 14/01/2022
 *
 * @author Rudenko Dmitry
 */
public class BG {
    public static void main(String[] args) throws IOException {

        String list = "[\n" +
                "    {\n" +
                "        \"id\": 102,\n" +
                "        \"title\": \"29.01.2022\",\n" +
                "        \"content\": \"Семейная классика) \uD83D\uDE82\uD83D\uDE82\uD83D\uDE82</br></br>#женапобедила\",\n" +
                "        \"tags\": [\n" +
                "            \"Билет на поезд\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/102/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/102/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 101,\n" +
                "        \"title\": \"21.01.2022\",\n" +
                "        \"content\": \"Попробовали новое дополнение к особняками.</br>Интересные новые механики. Новые загадки ещё не распробовали.</br></br>В завершение вечера пара партий в каркасон. </br>Под конец первой партии города затопило колой, но всё обошлось.\",\n" +
                "        \"tags\": [\n" +
                "            \"Особняки безумия\",\n" +
                "            \"Каркасон\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/101/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/101/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 100,\n" +
                "        \"title\": \"08.01.2022\",\n" +
                "        \"content\": \"Первая игра года - Миры Ктулху. Разложили два раза, огонь. </br>Круто что одна коробка до 4-ех человек.</br>Вечер ктулху продолжился новый сценарием Особняков.</br>Сыщики смогли защитить семью пожруги, и заточить монстра.\",\n" +
                "        \"tags\": [\n" +
                "            \"Особняки безумия\",\n" +
                "            \"Миры Ктулху\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/100/AXTeAj0VzzI.jpg\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/100/FZHYtHKhWec.jpg\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/100/gDoMzSik7ro.jpg\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/100/HxdfvxMJS6g.jpg\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/100/JO99nLf0kZo.jpg\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/100/yuFXJ9Ty4LA.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 17,\n" +
                "        \"title\": \"24.12.2021\",\n" +
                "        \"content\": \"Персонажи пытались сбежать от культистов, не хватило одного спасительного хода... </br></br>Ну а потом мы пошли мочить монстров. Как давно не играл в манчкин)\",\n" +
                "        \"tags\": [\n" +
                "            \"Особняки безумия\",\n" +
                "            \"Манчкин\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/17/unnamed.jpg\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/17/unnamed_1.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 16,\n" +
                "        \"title\": \"03.12.2021\",\n" +
                "        \"content\": \"Вот и окончились приключение, которое длилось два года.</br>Персонажи добились того, к чему стремились. Оставили после себя много чего, изменили мир.</br>Как нибудь ещё вернёмся во Флан, к старым знакомым\",\n" +
                "        \"tags\": [\n" +
                "            \"D&D\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/16/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/16/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/16/img_2.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": -15,\n" +
                "        \"title\": \"13.11.2021\",\n" +
                "        \"content\": \"Сыщики попробовали остановить монстра, заточив его в доме.</br>А потом двинулись строить свой город мечты.\",\n" +
                "        \"tags\": [\n" +
                "            \"Особняки безумия\",\n" +
                "            \"Мачи Коро\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-15/img.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 15,\n" +
                "        \"title\": \"29.10.2021\",\n" +
                "        \"content\": \"Опробовали доп с титанами. Боевики стало намного больше. Осталось в командный режим сыграть.</br></br>Босс монстр - прикольный филлер. Строишь комнаты с ловушками и монстрами, с определёнными сокровищами, что бы заманить к себе героев.\",\n" +
                "        \"tags\": [\n" +
                "            \"Киклады\",\n" +
                "            \"Босс монстр\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/15/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/15/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 14,\n" +
                "        \"title\": \"08.10.2021\",\n" +
                "        \"content\": \"Опробовали доп Аид, до титанов не добрались.</br>Но даже так битв было очень много, трое на одного, Харон без работы не остался\",\n" +
                "        \"tags\": [\n" +
                "            \"Киклады\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/14/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/14/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 13,\n" +
                "        \"title\": \"24.09.2021\",\n" +
                "        \"content\": \"В подземелье темные эльфы устроили засаду героям.</br>Но они просчитались.\",\n" +
                "        \"tags\": [\n" +
                "            \"D&D\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/13/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/13/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 10,\n" +
                "        \"title\": \"03.09.2021\",\n" +
                "        \"content\": \"Два моих топа: алхимики и Киклады.\",\n" +
                "        \"tags\": [\n" +
                "            \"Киклады\",\n" +
                "            \"Алхимики\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/10/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/10/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/10/img_2.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"title\": \"20.08.2021\",\n" +
                "        \"content\": \"Приключения в подземелье продолжается. Найден убуйца, безумный жрец псевдо поклонник демонов.</br></br>Ну и пару партий в каркасон)\",\n" +
                "        \"tags\": [\n" +
                "            \"D&D\",\n" +
                "            \"Каркасон\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/9/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/9/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/9/img_2.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 11,\n" +
                "        \"title\": \"13.08.2021\",\n" +
                "        \"content\": \"Дройды круче всех \uD83E\uDDBE\",\n" +
                "        \"tags\": [\n" +
                "            \"ЗВ Внешнее кольцо\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/11/img.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"title\": \"30.07.2021\",\n" +
                "        \"content\": \"Diskwars - эта игра ждала 6 лет. Интересный варгейм, будем пробовать ещё.</br></br>Котики - милейший арт и мозголомный геймплей. Набрал 51 очко, но при равенстве проиграл, меньше котиков прикормил.</br></br>Фонарики - давно не доставал с полки, но все равно набрать большее количество очков.\",\n" +
                "        \"tags\": [\n" +
                "            \"Diskwars\",\n" +
                "            \"Котики\",\n" +
                "            \"Фонарики\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/8/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/8/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/8/img_2.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/8/img_3.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/8/img_4.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": -8,\n" +
                "        \"title\": \"16.07.2021\",\n" +
                "        \"content\": \"Классика, но с мостами. </br>А потом крылья, три партии пролетели незаметно. Очень красочно и интересно.\",\n" +
                "        \"tags\": [\n" +
                "            \"Каркасон\",\n" +
                "            \"Крылья\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-8/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-8/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-8/img_2.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"title\": \"11.06.2021\",\n" +
                "        \"content\": \"Три героя спустились в подземелье, и зачистили его до такой степени, что в конце начали убивать друг друга ради победы.</br>Ну а на Марсе победила строительная компания, хотя озеленители были близки.</br>99 очков\",\n" +
                "        \"tags\": [\n" +
                "            \"Подземелья\",\n" +
                "            \"Покорение Марса\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/7/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/7/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/7/img_2.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/7/img_3.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/7/img_4.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"title\": \"28.05.2021\",\n" +
                "        \"content\": \"Поляки одержали победу (30 очков от преследователей), хотя их пытались сломить.</br>А вот японцы не дотянули 6 очков до лидера, все решили порушать мне здания.\",\n" +
                "        \"tags\": [\n" +
                "            \"Серп\",\n" +
                "            \"Поселенцы\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/6/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/6/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"title\": \"14.05.2021\",\n" +
                "        \"content\": \"Четыре героя пытались победить королеву паучиху.</br> Но не смогли, запутались в паутине.\",\n" +
                "        \"tags\": [\n" +
                "            \"Runebound\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/5/img.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"title\": \"23.04.2021\",\n" +
                "        \"content\": \"Вечер филлеров.\",\n" +
                "        \"tags\": [\n" +
                "            \"Каркасон\",\n" +
                "            \"Наместник\",\n" +
                "            \"Саграда\",\n" +
                "            \"Поселенцы\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/4/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/4/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/4/img_2.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/4/img_3.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/4/img_4.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": -4,\n" +
                "        \"title\": \"09.04.2021\",\n" +
                "        \"content\": \"Следите, где устраиваете привал.\",\n" +
                "        \"tags\": [\n" +
                "            \"D&D\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-4/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-4/img_1.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"title\": \"26.03.2021\",\n" +
                "        \"content\": \"Наконец-то провёл сессию, полгода откладывал.</br>Надо находить баланс между боевками и социалкой, а то даже я устал кидать Кубы.</br>Но в принципе прошло весело, главное что собрались\",\n" +
                "        \"tags\": [\n" +
                "            \"D&D\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/3/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/3/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/3/img_2.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"title\": \"05.03.2021\",\n" +
                "        \"content\": \"В первый раз у троих игроков одинаковое количество очков (51), пришлось устраивать взрыв для определения победителя. А итоге победил Я)</br>Ну А в звездных войнах тактика диверсий и атак на патрули принесла мне победу.\",\n" +
                "        \"tags\": [\n" +
                "            \"Взрывная лаборатория\",\n" +
                "            \"ЗВ Внешнее кольцо\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/2/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/2/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/2/img_2.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/2/img_3.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/2/img_4.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": -2,\n" +
                "        \"title\": \"22.01.2021\",\n" +
                "        \"content\": \"Египет вырвался вперед, благодаря пирамидам и сфинксам. </br>А в алхимики удалось победить, только благодаря опровержению. И то, первое, второе и третья места с разницей по одному очку.\",\n" +
                "        \"tags\": [\n" +
                "            \"Поселенцы\",\n" +
                "            \"Алхимики\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-2/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/-2/img_2.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"15.01.2021\",\n" +
                "        \"content\": \"Римляне слабаки, зато Варвары в последнем раунде вырвались вперёд, и завоевали победу.</br>Ну а замки каркасона, строились по четкому плану градостроителей</br></br>А еще появилась монополия)\",\n" +
                "        \"tags\": [\n" +
                "            \"Поселенцы\",\n" +
                "            \"Каркасон\",\n" +
                "            \"Монополия\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/1/img_0.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/1/img_1.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/1/img_2.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/1/img_3.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/1/img_4.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/1/img_5.png\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 0,\n" +
                "        \"title\": \"06.01.2021\",\n" +
                "        \"content\": \"Наконец-то дошли руки до маленького мира. Полгода лежал на полке.</br>Ну и три партии в каркасон.</br>В итоге 4 партии, и везде победил.\",\n" +
                "        \"tags\": [\n" +
                "            \"Маленький мир\",\n" +
                "            \"Каркасон\"\n" +
                "        ],\n" +
                "        \"files\": [\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/0/img.png\",\n" +
                "            \"https://github.com/fso13/note-boardgames/raw/master/img/0/img_1.png\"\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        list = list.trim();
        JsonReader reader = new JsonReader(new StringReader(list));
        reader.setLenient(true);

        Type listType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        ArrayList<Note> notes = new Gson().fromJson(reader, listType);

        System.out.println(notes);
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta property=\"og:url\" content=\"https://fso13.github.io/note-boardgames/html/%s.html\"/>\n" +
                "    <meta property=\"og:type\" content=\"article\"/>\n" +
                "    <meta property=\"og:site_name\" content=\"Бардовский университет\"/>\n" +
                "    <meta property=\"og:title\" content=\"%s\"/>\n" +
                "    <meta property=\"og:description\" content=\"%s\"/>\n" +
                "    <meta property=\"og:image\" content=\"%s\"/>\n" +
                "    <meta property=\"og:image:alt\" content=\"%s\"/>\n" +
                "    <meta property=\"og:image:width\" content=\"550\"/>\n" +
                "    <meta property=\"og:image:height\" content=\"550\"/>\n" +
                "    <script\n" +
                "            src=\"https://code.jquery.com/jquery-3.6.0.min.js\"\n" +
                "            integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\"\n" +
                "            crossorigin=\"anonymous\"></script>\n" +
                "    </script>\n" +
                "    <link href=\"https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp\"></script>\n" +
                "\n" +
                "    <script src=\"../js/note.js\"></script>\n" +
                "    <script src=\"../js/games.js\"></script>\n" +
                "    <script src=\"../js/modal.js\"></script>\n" +
                "    <script src=\"../js/header.js\"></script>\n" +
                "</head>\n" +
                "<script>\n" +
                "    function copyToClipboard(id, el) {\n" +
                "        text = document.getElementById(id).href;\n" +
                "        navigator.clipboard.writeText(text).then(function () {\n" +
                "            alert(\"Ссылка скопированна в буффер\");\n" +
                "        }, function (err) {\n" +
                "            console.error('Async: Could not copy text: ', err);\n" +
                "        });\n" +
                "\n" +
                "    }\n" +
                "</script>\n" +
                "<body translate=\"no\">\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "<script defer>\n" +
                "    var nav = document.createElement('div');\n" +
                "    nav.className = 'flex flex-no-wrap font-mono';\n" +
                "    nav.innerHTML = headerHtml;\n" +
                "    nav.id = 'header';\n" +
                "\n" +
                "    document.body.appendChild(nav);\n" +
                "\n" +
                "    var mainDiv = document.createElement('div');\n" +
                "\n" +
                "    mainDiv.innerHTML = " +
                "        '<div style=\"width: 100%%; height: 100%%\" class=\"p-8  transition duration-200 ease-in-out z-10  top-0 right-0 bottom-0 left-0\" id=\"modal\">\\n' +\n" +
                "        '            <div role=\"alert\" class=\"container mx-auto  flex justify-center\">\\n' +\n" +
                "        '                <div class=\"relative w-11/12 sm:w-8/12 md:w-9/12 pt-10 pb-8 rounded\">\\n' +\n" +
                "        '                    <div class=\"flex flex-col items-center px-4 md:px-12\">\\n' +\n" +
                "        '                        <p class=\"pb-10 text-base sm:text-lg md:text-2xl font-bold md:leading-6 mt-6 text-center \">%s</p>\\n' +\n" +
                "        '                        <p class=\"focus:outline-none text-lg font-medium \">%s</p>\\n' +\n" +
                "        '                    <div>\\n' +\n" +
                "        '                    <div>\\n' +\n" +
                "        '                        <div class=\"flex p-8 flex-col items-center px-4 md:px-12\">\\n' +\n" +
                "        '                            <p  class=\"focus:outline-none text-sm text-sky-500 dark:text-sky-400 text-center\">%s</p>\\n' +\n" +
                "        '                        </div>" +
                "                               </div>' +\n" +
                "        '                      <div class=\"gap-x-1 gap-y-0 grid  grid-cols-3 hover:auto-rows-min\">%s</div>';\n" +
                "    mainDiv.className = 'rounded';\n" +
                "    document.getElementById('container').className = 'container grid';\n\n" +
                "    document.getElementById('container').appendChild(mainDiv);\n" +
                "</script>\n" +
                "\n" +
                "</html>\n";

        String htmlImageDiv =
                "                                   <div class=\"gap-x-5 gap-y-5  max-w-xs  flex flex-col justify-between \">\\n' +\n" +
                        "        '                              <img class=\"object-scale-down h-80 w-80 p-3 contrast-125 hue-rotate-15\" src=\"%s\">\\n' +\n" +
                        "        '                          </div>";


        notes.forEach(note -> {

            String images = note.files.stream().map(s -> String.format(htmlImageDiv, s)).collect(Collectors.joining());

            String total = String.format(html, note.id, note.content.replaceAll("<.*?>", ""), note.content.replaceAll("<.*?>", ""), note.files.get(0), note.content.replaceAll("<.*?>", ""), note.title, note.content, String.join(",", note.tags), images);

            try {
                Files.write(Paths.get("D:\\Projects\\note-boardgames\\html\\" + note.id + ".html"), total.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
