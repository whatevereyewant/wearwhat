document.addEventListener('DOMContentLoaded', function() {
  var form = document.querySelector('form[action="/mypage/update"]');
  // 명명된 이벤트 핸들러 함수
  function handleSubmit(event) {
    event.preventDefault();

    var formData = new FormData(form);
    fetch('/mypage/update', {
      method: 'POST',
      body: formData
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      alert(data.message);
    })
    .catch(error => {
      console.error('Error:', error);
      alert('저장에 실패했습니다.');
    });
  }

  // 이벤트 리스너 중복 등록 방지
  form.removeEventListener('submit', handleSubmit);
  form.addEventListener('submit', handleSubmit);
});
