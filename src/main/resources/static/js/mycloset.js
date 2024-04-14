// 추가한 부분~~~~
document.getElementById('userpic').addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const formData = new FormData();
            formData.append('image', file);
    
            sendByApi('POST', 'http://localhost:8080/upload', formData, (json) => {
                console.log('업로드 성공:', json);
            }, (json) => {
                console.error('업로드 실패:', json);
            });
            fetch('http://localhost:8080/predict', {
            method: 'POST',
            body: formData,
            // 'Content-Type': 'multipart/form-data' 헤더는 자동으로 설정됩니다.
            // 명시적으로 설정하지 않아도 됩니다.
            }).then(response => {
            return response.json();
            }).then(data => {
            // 'Name' 속성의 값을 콘솔에 출력합니다.
            console.log('Name:', data[0].Name);
            const prediction = data[0].Name; // 서버로부터 받은 값을 그대로 사용
            const picCategorySelectBox = document.getElementById('piccategory');
            // 셀렉트 박스의 옵션을 순회하면서 값 맞추기
            for (let i = 0; i < picCategorySelectBox.options.length; i++) {
                if (picCategorySelectBox.options[i].value === prediction) {
                    picCategorySelectBox.selectedIndex = i;
                    break;
                }
            }
            }).catch(error => {
            console.error('Error:', error);
            const prediction = data[0].Name; // 서버로부터 받은 값을 그대로 사용
            const picCategorySelectBox = document.getElementById('piccategory');
            // 셀렉트 박스의 옵션을 순회하면서 값 맞추기
            for (let i = 0; i < picCategorySelectBox.options.length; i++) {
                if (picCategorySelectBox.options[i].value === prediction) {
                    picCategorySelectBox.selectedIndex = i;
                    break;
                }
            }
            });
            }
    });
// document.getElementById('userpic').addEventListener('change', function(event) {
//     const file = event.target.files[0];
//     if (file) {
//         const formData = new FormData();
//         formData.append('image', file);

//         sendByApi('POST', 'http://127.0.0.1:5000/predict', formData, (json) => {
//             console.log('업로드 성공:', json);
//             const prediction = json.Name; // 서버로부터 받은 값을 그대로 사용
//             const picCategorySelectBox = document.getElementById('piccategory');
            
//             // 셀렉트 박스의 옵션을 순회하면서 값 맞추기
//             for (let i = 0; i < picCategorySelectBox.options.length; i++) {
//                 if (picCategorySelectBox.options[i].value === prediction) {
//                     picCategorySelectBox.selectedIndex = i;
//                     break;
//                 }
//             }

//         }, (json) => {
//             console.error('업로드 실패:', json);
//             const prediction = json.Name; // 실패 시에도 동일하게 처리
//             const picCategorySelectBox = document.getElementById('piccategory');
            
//             for (let i = 0; i < picCategorySelectBox.options.length; i++) {
//                 if (picCategorySelectBox.options[i].value === prediction) {
//                     picCategorySelectBox.selectedIndex = i;
//                     break;
//                 }
//             }
//         });
//     }
// }); 




document.getElementById('userpic').addEventListener('change', function(event) {
    if (event.target.files && event.target.files[0]) {
        const reader = new FileReader();
        
        reader.onload = function(e) {
            // 이미지 객체를 생성합니다.
            const img = new Image();
            img.src = e.target.result;
            
            img.onload = function() {
                // Canvas를 사용하여 이미지 사이즈 조절
                const maxWidth = 150;
                const maxHeight = 150;
                let width = img.width;
                let height = img.height;
                
                // 너비와 높이를 비율에 맞춰 조절
                if (width > height) {
                    if (width > maxWidth) {
                        height *= maxWidth / width;
                        width = maxWidth;
                    }
                } else {
                    if (height > maxHeight) {
                        width *= maxHeight / height;
                        height = maxHeight;
                    }
                }
                
                // Canvas를 생성하고 조절된 사이즈로 이미지를 그립니다.
                const canvas = document.createElement('canvas');
                canvas.width = width;
                canvas.height = height;
                const ctx = canvas.getContext('2d');
                ctx.drawImage(img, 0, 0, width, height);
                
                // Canvas에서 이미지를 Data URL로 변환합니다.
                const previewImage = document.getElementById('previewImage');
                previewImage.src = canvas.toDataURL('image/png');
                previewImage.style.display = 'block';
            };
        };
        
        reader.readAsDataURL(event.target.files[0]);
    }
});

const sendByApi = (method, url, formData, success_callback, failure_callback) => {
    let options = {
        method: method,
        body: formData
    };

    fetch(url, options)
        .then(response => response.json())
        .then(json => {
            if(json.ok) {
                if(success_callback) success_callback(json);
            } else {
                if(failure_callback) failure_callback(json);
            }
        })
        .catch(error => {
            console.error("API 호출 오류:", error);
        });
};

function submitForm() {
    var form = document.getElementById("uploadForm");
    var formData = new FormData(form);

    fetch("/myclosetupload/save", {
        method: "POST",
        body: formData
    })
    .then(response => {
        if(response.ok) {
            alert("데이터가 성공적으로 저장되었습니다.");
            // 성공 처리 로직
        } else {
            throw new Error('데이터 저장에 실패했습니다.');
        }
    })
    .catch(error => {
        alert(error.message);
    });
}
