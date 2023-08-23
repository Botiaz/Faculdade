#include <iostream>
using namespace std;

void func(int vet[5],int x){
  int maior=vet[0],menor=vet[0];
  for(int i=1;i<5;i++){
    if(vet[i]>maior){
      maior=vet[i];
    }
    if(vet[i]<menor){
      menor=vet[i];
    }
  }
  cout<<"O maior numero e "<<maior;
  cout<<"\nO menor numero e "<<menor;
}

int main() {
  int x, vet[5]={2,9,4,31,14};
  func(vet,x);
}