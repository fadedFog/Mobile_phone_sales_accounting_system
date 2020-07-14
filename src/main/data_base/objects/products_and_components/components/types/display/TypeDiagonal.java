package main.data_base.objects.products_and_components.components.types.display;


public enum TypeDiagonal{
    D6_3(6.3), D5_5(5.5), D6_7(6.7);

    double diagonal;
    TypeDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }
    public double getDiagonal() {
        return diagonal;
    }
    public static String getOrigDiagonal(double diagonal){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < TypeDiagonal.values().length; i++){
            if(diagonal == TypeDiagonal.values()[i].getDiagonal()){
                result.append(TypeDiagonal.values()[i].toString());
                i = TypeDiagonal.values().length;
            }
        }

        return result.toString();
    }
}
