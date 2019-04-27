// 生成隨機數
function getRandomNum() {
    var a = [];
    var b = [];

    for(var i = 0; i < 9; i++) {
        a.push(i + 1);
    }
    
    while(a.length > 0) {
        var random = Math.floor(Math.random() * a.length);
        b.push(a[random]);
        a.splice(random, 1);
    }

    return b;
}

// 生成空數組
function getEmptyArray() {
    var a = [];
    for(var i = 0; i < 9; i++) {
        a.push(0);
    }

    return a;
}

// 生成列數據
function generateColsData(row, col) {
    cols[col][row] = rows[row][col];
}

// 生成宮數據
function generateZonsData(row, col) {
    var zon = Math.floor(row / 3) * 3 + Math.floor(col / 3) ;
    var ind = Math.floor(row % 3) * 3 + Math.floor(col % 3);
    zons[zon][ind] = rows[row][col];
}

// 生成行數據
function generateRowsData(row, col) {
    var zon = Math.floor(row / 3) * 3 + Math.floor(col / 3) ;

    var result = [];
    Array.prototype.push.apply(result, rows[row]);
    Array.prototype.push.apply(result, cols[col]);
    Array.prototype.push.apply(result, zons[zon]);

    for(var i = 0; i < result.length - 1; i++) {
        for(var j = i + 1; j < result.length; j++) {
            if(result[i] == result[j]) {
                result.splice(j, 1);
                j--;
            }
        }
    }

    for(var i = 0; i < result.length; i++) {
        if(result[i] == 0) {
            result.splice(i, 1);
            i--;
        }
    }

    var total = [];
    for(var i = 0; i < 9; i++) {
        total.push(i + 1);
    }

    for(var i = 0; i < total.length; i++) {
        var flag = false;
        for(var j = 0; j < result.length; j++) {
            if(total[i] == result[j]) {
                flag = true;
                break;
            }
        }

        if(flag) {
            total.splice(i, 1);
            i--;
        }
    }

    var random = Math.floor(Math.random() * total.length);

    return total[random];
}

// 行數據錯誤，重置行數據
function resetRows(row) {
    for(var i = 0; i < rows[row].length; i++) {
        rows[row][i] = 0;
        cols[i][row] = 0;
        var zon = Math.floor(row / 3) * 3 + Math.floor(i / 3) ;
        var ind = Math.floor(row % 3) * 3 + Math.floor(i % 3);
        zons[zon][ind] = 0;
    }
}

var rows = [];
var cols = [];
var zons = [];

// 生成整個數據
function generateJoy() {
    for(var i = 0; i < 9; i++){
        rows.push(getEmptyArray());
        cols.push(getEmptyArray());
        zons.push(getEmptyArray());
    }
    rows[0] = getRandomNum();
    for(var i = 0; i < rows[0].length; i++) {
        generateColsData(0, i);
        generateZonsData(0, i);
    }
    for(var i = 1; i < rows.length; i++) {
        var flag = false;
        for(var j = 0; j < rows[i].length; j++) {
            var data = generateRowsData(i, j);
            if(typeof(data) != "undefined")  {
                rows[i][j] = data;
                generateColsData(i, j);
                generateZonsData(i, j);
            } else {
                flag = true;
                break;
            }
        }
    
        if(flag) {
            resetRows(i);
            i--;
        }
    }
    console.log(rows);
}
generateJoy();

