<%--
  Created by IntelliJ IDEA.
  User: jungwoosong
  Date: 11/14/24
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="first-page">
    <div id="map" class="w-full min-h-screen z-1"></div>
    <div class="absolute z-50 w-full top-5 left-1/2 -translate-x-1/2 max-w-xl px-2">
        <input class="p-4 border rounded w-full shadow focus:outline-none focus:ring-2 focus:ring-zinc-900"
               placeholder="장소를 입력하세요" id="search-input" autocomplete="off"/>
    </div>
    <button class="absolute z-50 bottom-10 right-5 w-full px-2 py-3 bg-white rounded-full shadow max-w-32 hover:bg-zinc-900 hover:text-white transition-colors hidden"
            id="btn-create">
        <div class="flex flex-row gap-2 items-center justify-center">
            <span>파티 만들기</span>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="size-5">
                <path stroke-linecap="round" stroke-linejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3"/>
            </svg>
        </div>
    </button>
    <div class="absolute z-50 bottom-10 left-1/2 -translate-x-1/2 w-full px-2">
        <div class="w-full max-w-xl mx-auto relative bottom-2 right-0">
            <button class="w-10 h-10 bg-white rounded-lg border flex justify-center items-center hover:bg-zinc-900 hover:text-white transition-colors touch-none" id="btn-user-location">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M15 10.5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                    <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 10.5c0 7.142-7.5 11.25-7.5 11.25S4.5 17.642 4.5 10.5a7.5 7.5 0 1 1 15 0Z" />
                </svg>
            </button>
        </div>
        <div class="w-full max-w-xl mx-auto p-2 bg-white border rounded-lg shadow ">
            <div class="flex flex-col h-full justify-between">
                <div class="flex flex-col gap-2 mb-2">
                    <div class="flex flex-col gap-1">
                        <h6 class="text-sm text-gray-400">
                            출발지
                        </h6>
                        <h4 class="text-md font-bold" id="selected-start-point"></h4>
                    </div>
                    <div class="flex flex-col gap-1">
                        <h6 class="text-sm text-gray-400">
                            도착지
                        </h6>
                        <h4 class="text-md font-bold" id="selected-release-point"></h4>
                    </div>
                </div>
                <button class="w-full rounded bg-zinc-900 text-white p-2" id="btn-next">만들기</button>
            </div>
        </div>
    </div>
</div>
<div id="second-page" class="w-full min-h-dvh bg-gray-100 flex justify-center items-start md:items-center px-2 hidden">
    <form class="w-full max-w-lg mx-auto min-h-32 bg-white rounded border p-4 flex flex-col gap-3 mt-2" action="${pageContext.request.contextPath}/party" method="post">
        <div class="flex flex-col gap-1 w-full">
            <label for="form-party-type" class="text-lg font-bold">
                유형
            </label>
            <select id="form-party-type" name="type">
                <option value="taxi">택시</option>
                <option value="carpool">카풀</option>
            </select>
        </div>
        <div class="w-full flex flex-row items-center justify-between">
            <label for="form-start-point-name" class="text-lg font-bold">
                출발지
            </label>
            <input type="text"
                   class="p-2 rounded border text-gray-400 font-bold text-right"
                   id="form-start-point-name"
                   name="start-point-name"
                   readonly/>

            <input type="text"
                   class="p-2 rounded border text-gray-400 font-bold text-right text-sm hidden"
                   id="form-start-point-address"
                   name="start-point-address"
                   readonly />
        </div>
        <div class="w-full flex flex-row items-center justify-between">
            <label for="form-release-point-name" class="text-lg font-bold">
                도착지
            </label>
            <input type="text"
                   class="p-2 rounded border text-gray-400 font-bold text-right"
                   id="form-release-point-name"
                   name="release-point-name"
                   readonly/>

            <input type="text"
                   class="p-2 rounded border text-gray-400 font-bold text-right text-sm hidden"
                   id="form-release-point-address"
                   name="release-point-address"
                   readonly />
        </div>
        <div class="flex flex-row gap-1">
            <div class="flex flex-col gap-1 w-full">
                <label for="form-party-size" class="text-lg font-bold">
                    승차 인원 수
                </label>
                <input type="number" id="form-party-size"
                       class="h-10 p-2 border rounded focus:ring-2 focus:ring-zinc-900 focus:outline-none"
                       min="1" max="45"
                       name="size"
                       inputmode="numeric" pattern="[0-9]*"/>
            </div>
            <div class="flex flex-col gap-1 w-full">
                <label for="form-party-datetime" class="text-lg font-bold">
                    승차일
                </label>
                <input type="datetime-local"
                       id="form-party-datetime"
                       name="datetime"
                       class="h-10 p-2 border rounded focus:ring-2 focus:ring-zinc-900 focus:outline-none"/>
            </div>
        </div>
        <div class="flex flex-col gap-1">
            <label for="form-party-title" class="text-lg font-bold">
                파티 이름
            </label>
            <input type="text"
                   id="form-party-title"
                   name="title"
                   class="h-10 p-2 border rounded focus:ring-2 focus:ring-zinc-900 focus:outline-none"/>
        </div>
        <div class="flex flex-col gap-1">
            <label for="form-party-description" class="text-lg font-bold">
                파티 설명
            </label>
            <textarea id="form-party-description"
                      name="description"
                      class="max-h-44 p-2 border rounded focus:ring-2 focus:ring-zinc-900 focus:outline-none"></textarea>
        </div>
        <div class="fixed w-full md:max-w-lg left-0 md:left-1/2 bottom-0 md:bottom-5 md:-translate-x-1/2">
            <button class="p-4 bg-zinc-900 text-white md:rounded-lg rounded-t-lg w-full shadow hover:bg-zinc-800 transition-colors" type="submit">만들기</button>
        </div>
    </form>
</div>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8bc13d8d9a6f3d7e6922267272725c62&libraries=services"></script>
<script>
    window.onload = function () {
        const formDatetime = document.getElementById("form-party-datetime");
        const now = new Date().toISOString().slice(0,new Date().toISOString().lastIndexOf(":"));
        formDatetime.min = now;
        formDatetime.value = now;
    }

    const firstPage = document.getElementById("first-page");
    const secondPage = document.getElementById("second-page");
    const searchInput = document.getElementById("search-input");
    const userLocationBtn = document.getElementById("btn-user-location");
    const nextBtn = document.getElementById("btn-next");

    const formStartPointName = document.getElementById("form-start-point-name");
    const formReleasePointName = document.getElementById("form-release-point-name");
    const formStartPointAddress = document.getElementById("form-start-point-address");
    const formReleasePointAddress = document.getElementById("form-release-point-address");

    let startPoint, releasePoint, openedInfoWindow = null;

    searchInput.addEventListener("change", (e) => places.keywordSearch(e.target.value, handleSearch))

    userLocationBtn.addEventListener("click", ()=> {
        getUserLocation();
    });

    nextBtn.addEventListener("click", ()=> {
        if(checkPointsSelected()){
            firstPage.classList.add('hidden');
            secondPage.classList.remove('hidden');

            // set place name input
            formStartPointName.value = startPoint.name;
            formReleasePointName.value = releasePoint.name;

            // set place address input
            formStartPointAddress.value = startPoint.address;
            formReleasePointAddress.value = releasePoint.address;
        }
    })

    const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    let options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.351788, 126.742894),
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    let places = new kakao.maps.services.Places();

    getUserLocation()

    function getUserLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                let lat = position.coords.latitude,
                    lng = position.coords.longitude;

                let location = new kakao.maps.LatLng(lat, lng);
                map.setCenter(location);
            })
        }
    }

    function handleSearch(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            var bounds = new kakao.maps.LatLngBounds();

            for (var i = 0; i < data.length; i++) {
                displayMarker(data[i]);
                bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
            }
            map.setBounds(bounds);
        }
    }

    function displayMarker(place) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x),
            zIndex: 3
        });

        var customInfoWindow = new kakao.maps.CustomOverlay({
            position: new kakao.maps.LatLng(place.y, place.x),
            content: "<div class='bg-white min-w-44 min-h-12 p-3 shadow rounded-lg border'>" +
                "<div class='absolute top-2 right-2'>" +
                "<button class='hover:text-red-500' onclick='openedInfoWindow.setMap(null)'>" +
                `<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-3"><path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" /></svg>` +
                "</button>" +
                "</div>"+
                "<div class='mt-1'>" +
                "<h1 class='font-bold text-md' id='place-name'></h1>" +
                "<h2 class='text-xs text-gray-500' id='place-address'></h2>"+
                "</div>" +
                "<div class='flex flex-row gap-1 mt-2'>"+
                "<button class='text-sm hover:text-blue-500' onclick='setStartPoint()'>출발지</button>" +
                "<button class='text-sm hover:text-blue-500' onclick='setReleasePoint()'>도착지</button>" +
                "</div>" +
                "</div>",
            yAnchor: 0.1,
            zIndex: 5
        })

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function () {
            if(openedInfoWindow) {
                openedInfoWindow.setMap(null);
            }
            customInfoWindow.setMap(map);
            openedInfoWindow = customInfoWindow;
            console.log(customInfoWindow);
            document.getElementById("place-name").innerHTML = place.place_name;
            document.getElementById("place-address").innerHTML = place.address_name;
        });
    }

    function setStartPoint() {
        document.getElementById("selected-start-point").innerHTML = document.getElementById("place-name").textContent;
        startPoint = {
            name: document.getElementById("place-name").textContent,
            address: document.getElementById("place-address").textContent
        }
    }

    function setReleasePoint() {
        document.getElementById("selected-release-point").innerHTML = document.getElementById("place-name").textContent;
        releasePoint = {
            name: document.getElementById("place-name").textContent,
            address: document.getElementById("place-address").textContent
        }
    }

    function checkPointsSelected() {
        if (startPoint == null || releasePoint == null) {
            return false;
        }
        return true;
    }
</script>

