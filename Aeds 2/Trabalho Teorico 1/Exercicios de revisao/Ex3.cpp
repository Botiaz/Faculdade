#include <iostream>
using namespace std;

void func(int vet[5],int x){
  int maior=0,menor=100000;
  for(int i=0;i<5;i++){
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