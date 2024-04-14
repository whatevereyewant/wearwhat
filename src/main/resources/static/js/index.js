const sendByApi = (method, url, params, success_callback, failure_callback) => {
    let content_type = 'application/json'
    let send_url =  url
    let options = {
        method: method,
        headers: {
            "Content-Type": content_type
        }
    }

    fetch(send_url, options)
        .then(response => {
            response.json()
                // API 호출 성공 (서버 호출 성공)
                .then(json => {
                    // 요청 결과 성공 
                    if(response.status >= 200 && response.status < 300) {  // 200 ~ 299
                        if(success_callback) {
                            alert("요청 결과 성공");
                            success_callback(json)
                        }
                    }
                    // 요청 결과 오류 
                    else {
                        if (failure_callback) {
                            alert("요청 결과 오류");
                            failure_callback(json)
                        }else {
                            alert(JSON.stringify(json))
                        }
                    }
                })
                // API 호출 오류 (서버 호출 오류)
                .catch(error => {
                    alert("API 호출 오류 (서버 호출 오류)");
                    alert(JSON.stringify(error))
                })
        })

}

const successFnc = (json) => {
    api_info['python']['tag'].innerHTML = JSON.stringify(json);
    const imageUrl = json.image_url;
    imageElement.src = imageUrl;
}
// const successFnc = (json) => {
//     // Python에서 받은 배열 데이터를 item_id로 활용하여 이미지 로드
//     json.forEach(item_id => {
//         // 이미지 경로 생성
//         const imgPath = `/Users/jimincheol/Documents/Project/HnM_Project_data/images/0${String(item_id).slice(0, 2)}/0${item_id}.jpg`;

//         // 이미지 요소 생성
//         const imgElement = new Image();
//         imgElement.src = imgPath; // 이미지 경로 설정하여 로드 시작

//         // 이미지 로드 후에 실행되는 함수 (옵션)
//         imgElement.onload = function() {
//             // 이미지가 로드된 후 실행할 코드
//             // 예: 이미지를 페이지에 추가
//             document.body.appendChild(imgElement); // body에 이미지 추가
//         };
//     });
// };

// const successFnc = (json) => {
//     const imageContainer = document.getElementById('imageContainer');

//     // Python에서 받은 이미지 정보(json)를 활용하여 이미지 로드
//     json.forEach(item_id => {
//         const imgPath = `/Users/jimincheol/Documents/Project/HnM_Project_data/images/0${String(item_id).slice(0, 2)}/0${item_id}.jpg`;
        
//         const imgElement = new Image();
//         imgElement.src = imgPath; // 이미지 경로 설정하여 로드 시작

//         imgElement.onload = function() {
//             // 이미지가 로드된 후 실행할 코드
//             imageContainer.appendChild(imgElement); // 이미지를 컨테이너에 추가
//         };
//     });
// };


// 예시 JSON 형태
// const exampleJson = [1, 2, 3, 4, 5];
// successFnc(exampleJson);


const failureFnc = (json) => {
    alert(JSON.stringify(json));
}


async function mainApi(url) {
    try {
        await sendByApi('get', url, successFnc, failureFnc);
    } catch(e) {
        console.log(e);
    }
}

const api_info = {
    "python": {
        "host":"http://localhost:8000/users/",
        "tag": document.getElementById("python")
    }
}

let sendApi = document.getElementById("sendApi");
let username = document.getElementById("username");
username.innerText;
sendApi.onsubmit = (event) => {
    // 리프레쉬 방지!!!
    event.preventDefault();

    
    let url = api_info['python']['host'] + username.innerText;
    

    alert("구매정보를 바탕으로 추천해드리겠습니다.( 소요시간 : 6분 )");
    mainApi(url);

};

