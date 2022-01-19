var headerHtml =

    '    <div style="height: 100vh;position: -webkit-sticky;position: sticky;" class="w-64  text-lg font-medium  top-0 z-50 sm:relative bg-gray-800 shadow md:h-full flex-col justify-between hidden sm:flex">\n' +
    '        <div class="px-8">\n' +
    '            <div class="focus:outline-none h-20 w-20 mb-4 lg:mb-0 mr-4 items-center">\n' +
    '                <img class="h-full w-full rounded-full overflow-hidden shadow items-center"  src="https://sun9-63.userapi.com/s/v1/if1/_86rarat6d7LPOE5G6LfVegJuItSP73RjyPkwFpimEMdzEdshZG8VighmXowAFCf3YFTnvUY.jpg?size=100x100&quality=96&crop=20,20,503,503&ava=1" alt="Logo">\n' +
    '            </div>\n' +
    '            <ul class="mt-12">\n' +
    '                <li class="flex w-full justify-between text-gray-300 cursor-pointer items-center mb-6">\n' +
    '                    <a href="/note-boardgames/index.html" class="flex items-center focus:outline-none focus:ring-2 focus:ring-white">\n' +
    '                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">' +
    '                           <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM7 9a1 1 0 100-2 1 1 0 000 2zm7-1a1 1 0 11-2 0 1 1 0 012 0zm-.464 5.535a1 1 0 10-1.415-1.414 3 3 0 01-4.242 0 1 1 0 00-1.415 1.414 5 5 0 007.072 0z" clip-rule="evenodd" />' +
    '                        </svg>\n' +
    '                        <span class="text-lg font-medium ml-2 ">Игротеки</span>\n' +
    '                    </a>\n' +
    '                    <div id="party" class="py-1 px-3 bg-gray-600 rounded text-gray-300 flex items-center justify-center text-xs">'+notes.length+'</div>\n' +
    '                </li>\n' +
    '                <li class="flex w-full justify-between text-gray-400 hover:text-gray-300 cursor-pointer items-center mb-6">\n' +
    '                    <a href="/note-boardgames/games.html" class="flex items-center focus:outline-none focus:ring-2 focus:ring-white">\n' +
    '                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">\n' +
    '                           <path d="M7 3a1 1 0 000 2h6a1 1 0 100-2H7zM4 7a1 1 0 011-1h10a1 1 0 110 2H5a1 1 0 01-1-1zM2 11a2 2 0 012-2h12a2 2 0 012 2v4a2 2 0 01-2 2H4a2 2 0 01-2-2v-4z" />\n' +
    '                        </svg>\n' +
    '                        <span class="text-lg font-medium ml-2">Игры</span>\n' +
    '                    </a>\n' +
    '                    <div id="games" class="py-1 px-3 bg-gray-600 rounded text-gray-300 flex items-center justify-center text-xs">'+games.length+'</div>\n' +
    '                </li>\n' +
    '                <li class="flex w-full justify-between text-gray-400 hover:text-gray-300 cursor-pointer items-center mb-6">\n' +
    '                    <a href="/note-boardgames/calendar.html" class="flex items-center focus:outline-none focus:ring-2 focus:ring-white">\n' +
    '                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">\n' +
    '                           <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />\n' +
    '                       </svg>\n' +
    '                        <span class="text-lg font-medium ml-2">Календарь</span>\n' +
    '                    </a>\n' +
    '                </li>\n' +
    '            </ul>\n' +

    '        </div>\n' +
    '    </div>\n' +
    '    <div id="container" class="container grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">\n' +
    '\n' +
    '    </div>\n' +
    '\n' +
    '\n' +
    '<script>\n' +
    '    var sideBar = document.getElementById("mobile-nav");\n' +
    '    var openSidebar = document.getElementById("openSideBar");\n' +
    '    var closeSidebar = document.getElementById("closeSideBar");\n' +
    '    sideBar.style.transform = "translateX(-260px)";\n' +
    '\n' +
    '    function sidebarHandler(flag) {\n' +
    '        if (flag) {\n' +
    '            sideBar.style.transform = "translateX(0px)";\n' +
    '            openSidebar.classList.add("hidden");\n' +
    '            closeSidebar.classList.remove("hidden");\n' +
    '        } else {\n' +
    '            sideBar.style.transform = "translateX(-260px)";\n' +
    '            closeSidebar.classList.add("hidden");\n' +
    '            openSidebar.classList.remove("hidden");\n' +
    '        }\n' +
    '    }\n' +
    '</script>';