#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "randomArray.h"
#include "joy.h"

int generateRow() {
    getRandomArray();
    for(int i = 0; i < sizeof(rows) / sizeof(rows[0]); i++) {
        for(int j = 0; j < sizeof(rows[0]) / sizeof(rows[0][0]); j++) {
            if(i == 0) {
                rows[i][j] = a[j];
                generateCol(i, j);
                generateZon(i, j);
            }

            
        }
    }

    return 0;
}

int generateCol(int row, int col) {
    cols[col][row] = rows[row][col];

    return 0;
}

int generateZon(int row, int col) {
    int zon = row / 3 * 3 + col / 3;
    int ord = row % 3 * 3 + col % 3;
    zons[zon][ord] = rows[row][col];

    return 0;
}

int printRows() {
    generateRow();
    for(int i = 0; i < sizeof(rows) / sizeof(rows[0]); i++) {
        for(int j = 0; j < sizeof(rows[0]) / sizeof(rows[0][0]); j++) {
            printf("%d ", rows[i][j]);
        }
        printf("\n");
    }

    return 0;
}