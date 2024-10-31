package com.parcial.mutantes.services;

import com.parcial.mutantes.entities.Adn;
import com.parcial.mutantes.repositories.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class AdnService {
    private final AdnRepository adnRepository;
    private static final int SEQUENCE_LENGTH=4;



@Autowired
public AdnService(AdnRepository adnRepository){

    this.adnRepository= adnRepository;

}

public static boolean esMutante (String[] adn){

    int sequenceCount =0;
    int n = adn.length;



    sequenceCount += checkRows(adn,n);
    if (sequenceCount>1) return true;

    sequenceCount += checkColumns(adn,n);
    if(sequenceCount>1) return true;

   sequenceCount += checkDiagonals(adn,n);


    return sequenceCount>1;

}

private static int checkRows(String[] adn,int n){
    int sequenceCount=0;

    for(int i=0; i<n; i++){
        int count =1;
        for (int j=1;j<n;j++){
            if (adn[i].charAt(j)==adn[i].charAt(j-1)){
                count ++;
                if (count == SEQUENCE_LENGTH){
                    sequenceCount++;
                    if(sequenceCount>1) return sequenceCount;
                }
            }else{
                count=1;
            }
        }
    }
    return sequenceCount;
}

    private static int checkColumns(String [] adn ,int n){
        int sequenceCount=0;

        for(int j=0; j<n;j++){
            int count =1;
            for (int i=1;i<n;i++){
                if (adn[i].charAt(j)==adn[i-1].charAt(j)){
                    count ++;
                    if (count == SEQUENCE_LENGTH){
                        sequenceCount++;
                        if(sequenceCount>1) return sequenceCount;
                    }
                }else{
                    count=1;
                }
            }
        }
        return sequenceCount;
    }


    private static int checkDiagonals(String[] adn, int n) {
        int sequenceCount = 0;

        // Diagonales de izquierda-arriba a derecha-abajo
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (checkDiagonal(adn, i, j, 1, 1, n)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }

        // Diagonales de izquierda-arriba a derecha-abajo
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = SEQUENCE_LENGTH - 1; j < n; j++) {
                if (checkDiagonal(adn, i, j, 1, -1, n)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }

        return sequenceCount;
    }

    private static boolean checkDiagonal(String[] adn, int x, int y, int dx, int dy, int n) {
        char first = adn[x].charAt(y);
       for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (newX >= n || newY >= n || newY < 0|| adn[newX].charAt(newY) != first) {
                return false;
            }
        }
        return true;
    }

//    private static int checkDiagonals (String[] adn, int n){
//        int sequenceCount=0;
//        //Diagonales de izquierda-arriba a derecha-abajo
//        for (int i=0; i <=n-SEQUENCE_LENGTH; i++){
//            for (int j=0; j <=n-SEQUENCE_LENGTH; j++){
//                if(checkDiagonal(adn,i,j,1,1,n)){
//                    sequenceCount++;
//                    if(sequenceCount>1)return sequenceCount;
//                }
//            }
//        }
//        //Diagonales de izquierda-arriba a derecha-abajo
//        for(int i=0;i<=n-SEQUENCE_LENGTH; i++){
//            for(int j= SEQUENCE_LENGTH-1;j<n;j++){
//                if(checkDiagonal(adn,i,j,1,-1,n)){
//                    sequenceCount++;
//                    if(sequenceCount>1)  return sequenceCount;
//                }
//            }
//        }
//        return sequenceCount;
//    }
//
//    private static boolean checkDiagonal(String[] adn,int x, int y, int dx, int dy,int n){
//     char first = adn[x].charAt(y);
//     for (int i=1 ; i < SEQUENCE_LENGTH; i++){
//         if (x+i*dx>= n || y+i*dy>=n|| y+i*dy <0 ){
//             return false;
//         }
//
//     }
//     return true;
//    }


  public boolean analyzeAdn (String[] adn){
    String adnSequence= String.join("," , adn);

      Optional<Adn> existingAdn= adnRepository.findByAdn(adnSequence);
      if (existingAdn.isPresent()){
          return existingAdn.get().isEsMutante();
      }




boolean esMutante= esMutante(adn);

Adn adnEntity= Adn.builder()

        .adn(adnSequence)
        .esMutante(esMutante)
        .build();

adnRepository.save(adnEntity);
return esMutante(adn);
 }
}