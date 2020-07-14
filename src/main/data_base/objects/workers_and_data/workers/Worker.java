package main.data_base.objects.workers_and_data.workers;

/** Сделать свойства переменных объекта обязательно заполняемыми! */

public class Worker {
    private Worker(){}

    private int serviceNumber;
    private String secondName;
    private String name;
    private String middleName;
    private String position;
    private String numberPhone;

    public int getServiceNumber() {
        return serviceNumber;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    public String getNumberPhone() {
        return numberPhone;
    }
    

    public static Builder newBuilder(){
        return new Worker().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setServiceNumber(int serviceNumber) {
            Worker.this.serviceNumber = serviceNumber;
            return this;
        }

        public Builder setSecondName(String secondName) {
            Worker.this.secondName = secondName;
            return this;
        }

        public Builder setName(String name) {
            Worker.this.name = name;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            Worker.this.middleName = middleName;
            return this;
        }

        public Builder setPosition(String position) {
            Worker.this.position = position;
            return this;
        }

        public Builder setNumberPhone(String numberPhone) {
            Worker.this.numberPhone = numberPhone;
            return this;
        }

        public Worker build(){
            return Worker.this;
        }
    }
}
