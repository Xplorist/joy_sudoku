#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "randomArray.h"

int initRandomArray() {
    srand(time(0));
    for(int i = 0; i < sizeof(a) / sizeof(int); i++) {
        int random = rand() % 9;
        if(b[random] != 0) {
            a[i] = b[random];
            b[random] = 0;
        } else {
            i--;
        }
    }

    return 0;
}

int initOrderArray() {
    for(int i = 0; i < sizeof(b) / sizeof(int); i++) {
        b[i] = i + 1;
    }

    return 0;
}

int printRandomArray() {
    for(int i = 0; i < sizeof(a) / sizeof(int); i++) {
        printf("a[%d]=%d\t",  i, a[i]);   
    }

    return 0;
}

int getRandomArray() {
    initOrderArray();
    initRandomArray();
    //printRandomArray();

    return 0;
}