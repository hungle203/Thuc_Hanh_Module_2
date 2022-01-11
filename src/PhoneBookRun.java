import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookRun {
    public static PhoneBookManagement phoneBooks = new PhoneBookManagement();

    public void run() {
        int choose;
        do {
            menu();
            choose = AppException.inputNumber();
            AppException.inputNumber(choose, 1, 8);
            switch (choose) {
                case 1:
                    showPhoneBook();
                    break;
                case 2:
                    addPhoneBook();
                    Const.SCANNER.nextLine();
                    break;

                case 3:
                    editPhoneBook();
                    break;
                case 4:
                    removePhoneBook();
                    break;
                case 5:
                    searchPhoneNumber();
                    break;
                case 6:
                    read();
                    break;
                case 7:
                    saveFile();
                    break;
            }
        } while (choose != 8);
    }

    private static void saveFile() {
        phoneBooks.saveFile();
    }

    private static void read() {
        phoneBooks.readFile();
    }

    private static void searchPhoneNumber() {
        System.out.println("Nhập số điện thoại tìm kiếm");
        String phoneSearch = Const.SCANNER.nextLine();
        for (PhoneBook phoneBook : phoneBooks.getPhoneBooks()) {
            if (phoneBook.getPhoneNumber().contains(phoneSearch)) {
                System.out.println(phoneBook);
            }
        }
    }

    private static void showPhoneBook() {
        String choose;
        int count = 5;
        int index = 0;
        do {
            if (phoneBooks.getPhoneBooks().size() == index) {
                System.out.println("Không có số điện thoại nào");
                break;
            }
            while (count > 0 && index < phoneBooks.getPhoneBooks().size()) {
                PhoneBook phoneBook = phoneBooks.getPhoneBooks().get(index);
                index++;
                System.out.println(phoneBook);
                count--;
            }
            System.out.println("Nhấn enter đế tiếp tục");
            choose = Const.SCANNER.nextLine();
            count = 5;
        } while (choose.equals(""));
    }

    private static void removePhoneBook() {
        String phoneNumber;
        do {
            System.out.println("Nhập vào số điện thoại bạn muốn xóa");
            phoneNumber = Const.SCANNER.nextLine();
            int index = phoneBooks.searchIndexByPhoneNumber(phoneNumber);
            if (index == -1) {
                System.err.println("Không tìm được danh bạ với số điện thoại trên.");
            } else {
                System.out.println("Chọn \"y\" để xác nhận xóa");
                String check = Const.SCANNER.nextLine();
                if (check.equals("y")) {
                    phoneBooks.remove(index);
                }
                break;
            }
        } while (!Objects.equals(phoneNumber, ""));

    }

    private void editPhoneBook() {
        String phoneNumber;
        do {
            System.out.println("Nhập vào số điện thoại bạn cần sửa");
            phoneNumber = Const.SCANNER.nextLine();
            int index = phoneBooks.searchIndexByPhoneNumber(phoneNumber);
            if (index == -1) {
                System.err.println("Không tìm được danh bạ với số điện thoại trên.");
            } else {
                PhoneBook phoneBook = inputPhoneBookEdit();
                phoneBook.setPhoneNumber(phoneNumber);
                phoneBooks.edit(index, phoneBook);
                break;
            }
        } while (!Objects.equals(phoneNumber, ""));
    }

    private void addPhoneBook() {
        PhoneBook phoneBook = createPhoneBook();
        phoneBooks.add(phoneBook);
    }
    private PhoneBook createPhoneBook(){
        System.out.println("Nhập số điện thoại");
        String numberPhone = inputPhoneBook();
        System.out.println("Nhóm của bạn là");
        String group = Const.SCANNER.nextLine();
        System.out.println("Họ tên");
        String fullName = Const.SCANNER.nextLine();
        System.out.println("Giới tính");
        String gender = Const.SCANNER.nextLine();
        System.out.println("Địa chỉ");
        String address = Const.SCANNER.nextLine();
        LocalDate dateOfBirth;
        do {
            System.out.println("Ngày sinh.");
            String dateString = Const.SCANNER.nextLine();
            dateOfBirth = AppException.inputDate(dateString);
        } while (dateOfBirth == null);
        String email = inputEmail();
        return new PhoneBook(numberPhone, group, fullName, gender, address, dateOfBirth, email);
    }

    private PhoneBook inputPhoneBookEdit() {
        System.out.println("Nhóm của danh bạ.");
        String group = Const.SCANNER.nextLine();
        System.out.println("Họ tên.");
        String fullName = Const.SCANNER.nextLine();
        System.out.println("Giới tính");
        String gender = Const.SCANNER.nextLine();
        System.out.println("Địa chỉ.");
        String address = Const.SCANNER.nextLine();
        LocalDate dateOfBirth;
        do {
            System.out.println("Ngày sinh.");
            String dateString = Const.SCANNER.nextLine();
            dateOfBirth = AppException.inputDate(dateString);
        } while (dateOfBirth == null);
        String email = inputEmail();
        return new PhoneBook(group, fullName, gender, address, dateOfBirth, email);
    }


    private String inputPhoneBook() {
        String numberPhone;
        final String PHONE = "^[0][235789]\\d{8}$";
        Pattern patternPhone = Pattern.compile(PHONE);
        Matcher matcherPhone;
        int index;
        do {
            System.out.println("Số điện thoại : ");
            numberPhone = Const.SCANNER.nextLine();
            matcherPhone = patternPhone.matcher(numberPhone);
            index = phoneBooks.searchIndexByPhoneNumber((numberPhone));
            if (index != -1) {
                System.out.println("SỐ ĐIỆN THOẠI ĐÃ TỒN TẠI TRONG DANH BẠ");
            }
            if (!matcherPhone.matches()) {
                System.out.println("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ\nSố điện thoại phải bắt đầu bằng số 0 và có 10 số");
            }
        } while (!matcherPhone.matches() || index != -1);
        return numberPhone;
    }

    private String inputEmail() {
        String email;
        final String EMAIL = "^[a-zA-Z]+[A-Za-z0-9]{7}+@[a-zA-Z]+(\\.com)$";
        Pattern patternEamil = Pattern.compile(EMAIL);
        Matcher matcherEmail;
        do {
            System.out.println("Email: ");
            email = Const.SCANNER.nextLine();
            matcherEmail = patternEamil.matcher(email);
            if (!matcherEmail.matches()) {
                System.out.println("Email không hợp lệ mời nhập lại. Email phải có ít nhất 8 kí tự");
            }
        } while (!matcherEmail.matches());
        return email;
    }

    public static void menu() {
        System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhập");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng");
    }

}
