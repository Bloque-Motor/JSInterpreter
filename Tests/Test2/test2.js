function int test2(int i, boolean ok){
    var int num;
    input(num);
    while(!ok){
        num -= i;
        if (num < ok) ok = true;
    }
    var string hola = 'Hola caracola';
    print(hola);
    return num;
}