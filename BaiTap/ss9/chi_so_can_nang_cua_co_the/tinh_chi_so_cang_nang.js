function kiem_tra(){
    let weight = document.getElementById('can_nang').value;
    let hight = document.getElementById('chieu_cao').value;
    let bmi = weight / (Math.pow(hight, 2));
    let result = '';
    if (bmi < 18) {
        result = 'Underweight';
    } else if (bmi < 25) {
        result = 'Normal';
    } else if (bmi < 30) {
        result = 'Overweight';
    } else {
        result = 'Obese';
    }
    document.getElementById('result').innerHTML = "Chỉ số BMI là: " + bmi +'&nbsp;'+'&nbsp;'+ '<b>'+ result + '</b>';
}