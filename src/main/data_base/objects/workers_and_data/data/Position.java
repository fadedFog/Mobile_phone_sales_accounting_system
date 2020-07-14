package main.data_base.objects.workers_and_data.data;

public enum  Position {
    SELLER("Продавец"),
    SALES_MANAGER("Менеджер по продажам"),
    DELIVERY_MANAGER("Менеджер по доставкам"),
    ASSOCIATE_DIRECTOR("Заместитель директора"),
    DIRECTOR("Директор");

    private String post;
    Position(String post) {
        this.post = post;
    }
    public String getPost() {
        return post;
    }
    public static String getOrigPost(String post){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < Position.values().length; i++){
            if(post.equals(Position.values()[i].getPost())){
                result.append(Position.values()[i].toString());
                i = Position.values().length;
            }
        }

        return result.toString();
    }
}
