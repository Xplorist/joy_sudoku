#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "randomTest.h"

int main() {
    int *p;
    p = test();
    for(int i = 0; i < 9; i++) {
        printf("a[%d]=%d ",  i, *(p + i));
    }

    return 0;
}

int * test() {
    static int a[9];
    int b[9];

    for(int i = 0; i < sizeof(b) / sizeof(int); i++) {
        b[i] = i + 1;
    }

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

    return a;
}