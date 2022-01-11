package view;

import model.PhoneBook;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PhoneBookManagement {
    public static final String FILE_PATCH = "D:\\IDEA\\Bai_Thi_Thuc_Hanh-Module_2\\src\\phoneBook.csv";
    private ArrayList<PhoneBook> phoneBooks = new ArrayList<>();

    public ArrayList<PhoneBook> getPhoneBooks() {
        return phoneBooks;
    }

    public int searchIndexByPhoneNumber(String phoneNumber) {
        int index = -1;
        for (int i = 0; i < phoneBooks.size(); i++) {
            PhoneBook phoneBook = phoneBooks.get(i);
            if (phoneBook.getPhoneNumber().equals(phoneNumber)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void setPhoneBooks(ArrayList<PhoneBook> phoneBooks) {
        this.phoneBooks = phoneBooks;
    }

    public void add(PhoneBook phoneBook) {
        phoneBooks.add(phoneBook);
    }

    public void remove(int index) {
        phoneBooks.remove(index);
    }

    public void edit(int index, PhoneBook phoneBook) {
        phoneBooks.set(index, phoneBook);
    }

    public void readFile() {
        File file = new File(FILE_PATCH);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.err.println("Đọc file thành công");

            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] data = line.split(",");
                String phoneNumber = data[0];
                String groupOfContacts = data[1];
                String fullName = data[2];
                String gender = data[3];
                String address = data[4];
                LocalDate dateOfBirth = LocalDate.parse(data[5]);
                String email = data[6];
                PhoneBook phoneBook = new PhoneBook(phoneNumber,groupOfContacts, fullName, gender, address, dateOfBirth, email);
                phoneBooks.add(phoneBook);
                System.out.println(phoneBook);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException var12) {
            System.err.println("File data không tồn tại");
        } catch (IOException var13) {
            System.err.println("Lỗi đọc file");
        }

    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATCH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.err.println("Lưu file thành công");
            for (PhoneBook phoneBook : phoneBooks) {
                bufferedWriter.write(phoneBook.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException var5) {
            System.err.println("Lỗi lưu file");
        }
    }
}
