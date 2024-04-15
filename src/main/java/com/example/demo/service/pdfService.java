package com.example.demo.service;
import com.example.demo.model.Image;
import com.example.demo.model.coordinates;
import com.example.demo.model.item;
import com.example.demo.model.pdf;
import com.example.demo.repo.coorDinatesRepo;
import com.example.demo.repo.imageRepository;
import com.example.demo.repo.itemRepository;
import com.example.demo.repo.pdfRepository;
import io.minio.*;
import io.minio.errors.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
public class pdfService {
//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private itemRepository itemRepository;
    
//    public byte[] getReport() throws IOException, JRException {
//        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<!-- Created with Jaspersoft Studio version 6.20.6.final using JasperReports Library version 6.20.6-5c96b6aa8a39ac1dc6b6bea4b81168e16dd39231  -->\n" +
//                "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"Blank_A4_2\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"7a8bec98-c929-4dad-95d6-6ba290961d40\">\n" +
//                "    <property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"One Empty Record\"/>\n" +
//                "    <queryString>\n" +
//                "        <![CDATA[]]>\n" +
//                "    </queryString>";
////        String content = "<field name=\"name\" class=\"java.lang.String\">\n" +
////                "        <property name=\"com.jaspersoft.studio.field.name\" value= \"\"/>\n" +
////                "    </field>\n" +
////                "\n" +
////                "    <field name=\"id\" class=\"java.lang.Integer\">\n" +
////                "    <property name=\"com.jaspersoft.studio.field.name\" value= \"\"/>\n" +
////                "    </field>\n" +
////                "    <field name=\"gender\" class=\"java.lang.Integer\">\n" +
////                "    <property name=\"com.jaspersoft.studio.field.name\" value= \"\"/>\n" +
////                "    </field>";
//        String footer = "<background>\n" +
//                "        <band splitType=\"Stretch\"/>\n" +
//                "    </background>\n" +
//                "    <title>\n" +
//                "        <band height=\"94\" splitType=\"Stretch\">\n" +
//                "            <staticText>\n" +
//                "                <reportElement x=\"-10\" y=\"5\" width=\"131\" height=\"25\" uuid=\"18392cfc-6f7c-4f99-9e04-29656508aea6\"/>\n" +
//                "                <textElement textAlignment=\"Center\">\n" +
//                "                    <font fontName=\"Arial\" size=\"12\"/>\n" +
//                "                </textElement>\n" +
//                "                <text><![CDATA[name]]></text>\n" +
//                "            </staticText>\n" +
//                "            <textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\n" +
//                "                <reportElement x=\"-10\" y=\"36\" width=\"131\" height=\"25\" uuid=\"8b541074-2a99-4650-a73e-819004ce3edc\"/>\n" +
//                "                <textElement textAlignment=\"Center\">\n" +
//                "                    <font fontName=\"Arial\" size=\"10\"/>\n" +
//                "                </textElement>\n" +
//                "                <textFieldExpression><![CDATA[$F{name}]]></textFieldExpression>\n" +
//                "            </textField>\n" +
//                "            <staticText>\n" +
//                "                <reportElement x=\"120\" y=\"5\" width=\"121\" height=\"25\" uuid=\"5fae7d32-eba0-468e-a024-3d595d23ea82\"/>\n" +
//                "                <textElement textAlignment=\"Center\">\n" +
//                "                    <font fontName=\"Arial\" size=\"12\"/>\n" +
//                "                </textElement>\n" +
//                "                <text><![CDATA[id]]></text>\n" +
//                "            </staticText>\n" +
//                "            <textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\n" +
//                "                <reportElement x=\"120\" y=\"37\" width=\"121\" height=\"23\" uuid=\"7a43830c-560e-493a-aab2-39a81be93c85\"/>\n" +
//                "                <textElement>\n" +
//                "                    <font fontName=\"Arial\" size=\"10\"/>\n" +
//                "                </textElement>\n" +
//                "                <textFieldExpression><![CDATA[$F{id}]]></textFieldExpression>\n" +
//                "            </textField>\n" +
//                "            <staticText>\n" +
//                "                <reportElement x=\"241\" y=\"5\" width=\"161\" height=\"25\" uuid=\"aeb9d180-fe30-46a1-894e-ef9e0c57eafb\"/>\n" +
//                "                <textElement textAlignment=\"Center\">\n" +
//                "                    <font fontName=\"Arial\" size=\"12\"/>\n" +
//                "                </textElement>\n" +
//                "                <text><![CDATA[gender]]></text>\n" +
//                "            </staticText>\n" +
//                "            <textField>\n" +
//                "                <reportElement x=\"241\" y=\"37\" width=\"161\" height=\"24\" uuid=\"b7d24987-d590-4f98-a4f4-bcac4eba5979\"/>\n" +
//                "                <textElement textAlignment=\"Center\">\n" +
//                "                    <font fontName=\"Arial\" size=\"10\"/>\n" +
//                "                </textElement>\n" +
//                "                <textFieldExpression><![CDATA[$F{gender}]]></textFieldExpression>\n" +
//                "            </textField>\n" +
//                "        </band>\n" +
//                "    </title>\n" +
//                "    <pageHeader>\n" +
//                "        <band height=\"35\" splitType=\"Stretch\"/>\n" +
//                "    </pageHeader>\n" +
//                "    <columnHeader>\n" +
//                "        <band height=\"61\" splitType=\"Stretch\"/>\n" +
//                "    </columnHeader>\n" +
//                "    <detail>\n" +
//                "        <band height=\"125\" splitType=\"Stretch\"/>\n" +
//                "    </detail>\n" +
//                "    <columnFooter>\n" +
//                "        <band height=\"45\" splitType=\"Stretch\"/>\n" +
//                "    </columnFooter>\n" +
//                "    <pageFooter>\n" +
//                "        <band height=\"54\" splitType=\"Stretch\"/>\n" +
//                "    </pageFooter>\n" +
//                "    <summary>\n" +
//                "        <band height=\"42\" splitType=\"Stretch\"/>\n" +
//                "    </summary>\n" +
//                "</jasperReport>";
//        String finalcontent = header;
//        List<pdf> list = repository.findAll();
//        for (pdf i : list){
//           String content ="<field name=\"name\" class=\"java.lang.String\">\n" +
//                    "        <property name=\"com.jaspersoft.studio.field.name\" value= \""+i.getName()+"\"/>\n" +
//                    "    </field>\n" +
//                    "\n" +
//                    "    <field name=\"id\" class=\"java.lang.Integer\">\n" +
//                    "    <property name=\"com.jaspersoft.studio.field.name\" value= \""+i.getId()+"\"/>\n" +
//                    "    </field>\n" +
//                    "    <field name=\"gender\" class=\"java.lang.Integer\">\n" +
//                    "    <property name=\"com.jaspersoft.studio.field.name\" value= \""+i.getGender()+"\"/>\n" +
//                    "    </field>";
//           finalcontent += content;
//        }
//        finalcontent += footer;
//        JasperReport jasperReport = JasperCompileManager.compileReport(finalcontent);
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//        Map<String, Object> parameters = new HashMap<>();
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }

    public byte[] getReports(int id) throws IOException, JRException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("templates/item.jrxml").getInputStream());
            List<item> list = itemRepository.findAllById(Collections.singleton(id));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list, false);
            Map<String, Object> parameters = new HashMap<>();
            return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    public item add(item it){
        Optional<Image> imageid= imrepo.findById(it.getImageId());
        if(imageid.isPresent()){
            it.setImage(imageid.get().getUrl());
            return itemRepository.save(it);
        }
        return null;
    }
//    @Autowired
//    private dataseRepository dbrepository;
//    public itemDto add(itemDto it) {
//        Optional<Image> imageid = imrepo.findById(it.getImageId());
//        List<datase> dataid = dbrepository.findByItemId(it.getId());
//
//        if(imageid.isPresent()){
//            it.setImage(imageid.get().getUrl());
//           return modelMapper.map(
//                    itemRepository.save(modelMapper.map(it, item.class)),
//                    itemDto.class
//            );
//        }
//        if(CollectionUtils.isNotEmpty(dataid)){
//             it.setData(dataid);
//            return modelMapper.map(
//                    itemRepository.save(modelMapper.map(it, item.class)),
//                    itemDto.class
//            );
//                    }
//        return null;
//
//    }
    @Autowired
    private imageRepository imrepo;
    //private final Path imageDirectory = Paths.get("src/main/resources/static/images/");

//    public Resource dowloadImage(int id) throws MalformedURLException {
//        Path imagePath = imageDirectory.resolve(id+".Jpg");
//        Resource resource = new ClassPathResource((imagePath.toUri().toString()));
//        return resource;
//    }
public Optional<byte[]> getImageDataById(int id) {
    Optional<Image> imageOptional = imrepo.findById(id);
    return imageOptional.map(Image::getData);
}
@Autowired
private MinioClient minioClient;
    public  String uploadImage( MultipartFile file,String bucketName, String objectName, InputStream inputStream, String contentType ) throws Exception {
        String b ="http://localhost:8080/api/v1/getid?id=";
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setData(file.getBytes());
            imrepo.save(image);
            image.setUrl(b+image.getId());
            imrepo.save(image);
        try {
            // Kiểm tra xem bucket đã tồn tại chưa
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                // Nếu bucket chưa tồn tại, tạo mới
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // Upload đối tượng
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build()
            );

            System.out.println("Object created successfully");
        } catch (MinioException e) {
            System.err.println("Error occurred: " + e);
            throw new Exception("Error occurred while creating object in Minio", e);
        }
        return "image uploaded successfully";
    }
    public ResponseEntity<byte[]> getImage(int id){
        try {
            Image image = imrepo.findById(id).orElse(null);
            if(image != null){
                return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getData());
            }
            else {
                return  ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Autowired
    private  pdfRepository testRepository;
    public byte[] gettest() throws IOException, JRException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("templates/test.jrxml").getInputStream());
          List<pdf> tests =testRepository.findAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tests);
            Map<String, Object> parameters = new HashMap<>();
            return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    @Autowired
    private coorDinatesRepo coordinaterepo;
    public byte[] gettest1(int id) throws IOException, JRException {

        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!-- Created with Jaspersoft Studio version 6.20.6.final using JasperReports Library version 6.20.6-5c96b6aa8a39ac1dc6b6bea4b81168e16dd39231  -->\n" +
                "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"Blank_A4_4\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"268e891d-4386-495b-8537-7a684b5339f3\">\n" +
                "\t<property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"One Empty Record\"/>\n" +
                "\t<property name=\"net.sf.jasperreports.default.font.name\" value=\"Serif\"/>\n" +
                "\t<property name=\"net.sf.jasperreports.awt.ignore.missing.font\" value=\"true\"/>\n" +
                "\t<style name=\"Table_TH\" mode=\"Opaque\" backcolor=\"#F0F8FF\">\n" +
                "\t\t<box>\n" +
                "\t\t\t<pen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<topPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<leftPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<bottomPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<rightPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t</box>\n" +
                "\t</style>\n" +
                "\t<style name=\"Table_CH\" mode=\"Opaque\" backcolor=\"#BFE1FF\">\n" +
                "\t\t<box>\n" +
                "\t\t\t<pen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<topPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<leftPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<bottomPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<rightPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t</box>\n" +
                "\t</style>\n" +
                "\t<style name=\"Table_TD\" mode=\"Opaque\" backcolor=\"#FFFFFF\">\n" +
                "\t\t<box>\n" +
                "\t\t\t<pen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<topPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<leftPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<bottomPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t\t<rightPen lineWidth=\"0.5\" lineColor=\"#000000\"/>\n" +
                "\t\t</box>\n" +
                "\t</style>\n" +
                "\t<subDataset name=\"datases\" uuid=\"846913fc-589b-4130-9f42-1aa1e568f1eb\">\n" +
                "\t\t<queryString>\n" +
                "\t\t\t<![CDATA[]]>\n" +
                "\t\t</queryString>\n" +
                "\t</subDataset>\n" +
                "\t<queryString>\n" +
                "\t\t<![CDATA[]]>\n" +
                "\t</queryString>\n" +
                "\t<field name=\"pdf_name\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"name\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"nv\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"sex\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"cccd\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"ns\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"nc\" class=\"java.sql.Date\"/>\n" +
                "\t<field name=\"here\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"bhyt\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"phone_number\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"adress\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"job\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"work_place\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"previous_work1\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"date_start_work\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"year1\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"month1\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"to1\" class=\"java.sql.Date\"/>\n" +
                "\t<field name=\"year2\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"month2\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"since2\" class=\"java.sql.Date\"/>\n" +
                "\t<field name=\"to2\" class=\"java.sql.Date\"/>\n" +
                "\t<field name=\"family_illness\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"since1\" class=\"java.sql.Date\"/>\n" +
                "\t<field name=\"previous_work2\" class=\"java.lang.String\"/>\n" +
                "\t<field name=\"image\" class=\"java.lang.String\"/>\n" +
                "\t<background>\n" +
                "\t\t<band splitType=\"Stretch\"/>\n" +
                "\t</background>\n" +
                "\t<title>\n" +
                "\t\t<band height=\"88\" splitType=\"Stretch\">\n" +
                "\t\t\t<frame>\n" +
                "\t\t\t\t<reportElement x=\"0\" y=\"-2\" width=\"551\" height=\"82\" uuid=\"c6a7a75a-0b8c-4da1-b4e0-ea98b172ed43\"/>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"40\" y=\"1\" width=\"61\" height=\"20\" uuid=\"9fe68978-2d90-4fe0-9a15-7ea5835b6939\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[BỘ Y TẾ]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"3\" y=\"21\" width=\"141\" height=\"21\" uuid=\"9e2d263e-329a-4e33-b25f-2931e9659c1c\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[VIỆN SỨC KHỎE NGHỀ NGHIỆP]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"27\" y=\"45\" width=\"91\" height=\"19\" uuid=\"6590ae70-db6c-4401-a765-615a8f58e7c9\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[VÀ MÔI TRƯỜNG]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"25\" y=\"64\" width=\"91\" height=\"11\" uuid=\"71a579e7-2fec-4690-b504-76d069b176d4\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\" size=\"8\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[----------]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"340\" y=\"1\" width=\"210\" height=\"21\" uuid=\"200f9514-bdd6-4804-8ec9-ce625286db00\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\" isBold=\"true\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"375\" y=\"25\" width=\"139\" height=\"20\" uuid=\"66fcf2ae-bd68-45a2-aacd-4c62a7ab0add\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[Độc lập -Tự do - Hạnh phúc]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t\t<staticText>\n" +
                "\t\t\t\t\t<reportElement x=\"414\" y=\"45\" width=\"61\" height=\"19\" uuid=\"fec2e7fe-737e-4bb7-a0aa-3ea7bc8c00b2\"/>\n" +
                "\t\t\t\t\t<textElement textAlignment=\"Center\">\n" +
                "\t\t\t\t\t\t<font fontName=\"Times New Roman\"/>\n" +
                "\t\t\t\t\t</textElement>\n" +
                "\t\t\t\t\t<text><![CDATA[------------]]></text>\n" +
                "\t\t\t\t</staticText>\n" +
                "\t\t\t</frame>\n" +
                "\t\t</band>\n" +
                "\t</title>\n" +
                "\t<pageHeader>\n" +
                "\t\t<band height=\"221\" splitType=\"Stretch\">";
                String imga= "<image>\n" +
                        "\t\t\t\t<reportElement x=\"1\" y=\"31\" width=\"69\" height=\"80\" uuid=\"5633082b-3e12-4e7d-a591-4e4c6de3504e\"/>\n" +
                        "\t\t\t\t<imageExpression><![CDATA[$F{image}]]></imageExpression>\n" +
                        "\t\t\t</image>";
        String fooder = "</band>\n" +
                "\t</pageHeader>\n" +
                "\t<columnHeader>\n" +
                "\t\t<band height=\"70\" splitType=\"Stretch\"/>\n" +
                "\t</columnHeader>\n" +
                "\t<detail>\n" +
                "\t\t<band height=\"46\" splitType=\"Stretch\"/>\n" +
                "\t</detail>\n" +
                "\t<columnFooter>\n" +
                "\t\t<band height=\"90\" splitType=\"Stretch\"/>\n" +
                "\t</columnFooter>\n" +
                "\t<pageFooter>\n" +
                "\t\t<band height=\"54\" splitType=\"Stretch\"/>\n" +
                "\t</pageFooter>\n" +
                "\t<summary>\n" +
                "\t\t<band height=\"42\" splitType=\"Stretch\"/>\n" +
                "\t</summary>\n" +
                "</jasperReport>";
        String end=header+imga;
        List<coordinates> typeP = coordinaterepo.findByType("p");
        List<coordinates> typeF = coordinaterepo.findByType("f");
        List<item> list = itemRepository.findAllById(Collections.singleton(id));
        for(int i=0;i< list.size();i++) {
            for (int j = 0; j < typeP.size(); j++) {
            if (list.get(i).getSex()==null ) {
                typeP.remove(list.get(i).getSex());
            }
            if( list.get(i).getName()==null){
                   typeP.remove(list.get(i).getName());
            }
            if(list.get(i).getDate_start_work()==null ){
                   typeP.remove(list.get(i).getDate_start_work());
            }
            if(list.get(i).getAdress()=="null"){
                typeP.remove(j);
            }
            if(list.get(i).getBhyt()==null) {
                   typeP.remove(list.get(i).getBhyt());
               }
            if(list.get(i).getCccd()==null){
                typeP.remove(list.get(i).getCccd());
            }
            if(list.get(i).getFamily_illness()==null){
                typeP.remove(list.get(i).getFamily_illness());
            }
            if(list.get(i).getHere()==null){
                typeP.remove(list.get(i).getHere());
            }
            if(list.get(i).getJob()==null){
                typeP.remove(list.get(i).getJob());
            }
            if(list.get(i).getMonth1()==null){
                typeP.remove(list.get(i).getMonth1());
            }
            if(list.get(i).getMonth2()==null){
                typeP.remove(list.get(i).getMonth2());
            }
            if(list.get(i).getNc()==null ){
                typeP.remove(list.get(i).getNc());
            }
            if(list.get(i).getNs()==null){
                typeP.remove(list.get(i).getNs());
            }
            if(list.get(i).getNv()==null){
                typeP.remove(list.get(i).getNv());
            }
            if(list.get(i).getPhone_number()==null){
                typeP.remove(list.get(i).getPhone_number());
            }
            if(list.get(i).getPrevious_work1()==null){
                typeP.remove(list.get(i).getPrevious_work1());
            }
            if(list.get(i).getPrevious_work2()==null){
                typeP.remove(list.get(i).getPrevious_work2());
            }
            if(list.get(i).getSince1()==null){
                typeP.remove(list.get(i).getSince1());
            }
            if(list.get(i).getSince2()==null){
                typeP.remove(list.get(i).getSince2());
            }
            if(list.get(i).getTo1()==null){
                typeP.remove(list.get(i).getTo1());
            }
            if(list.get(i).getTo2()==null){
                typeP.remove(list.get(i).getTo2());
            }
            if(list.get(i).getYear1()==null ){
                typeP.remove(list.get(i).getYear1());
            }
            if(list.get(i).getYear2()==null){
                typeP.remove(list.get(i).getYear2());
            }
            if(list.get(i).getWork_place()==null){
                typeP.remove(list.get(i).getWork_place());
            }

                    end += "<staticText>\n" +
                            "\t\t\t\t<reportElement x=\"" + typeP.get(j).getX() + "\" y=\"" + typeP.get(j).getY() + "\" width=\"" + typeP.get(j).getWidth() + "\" height=\"" + typeP.get(j).getHeight() + "\" uuid=\"" + typeP.get(j).getUuid() + "\"/>\n" +
                            "\t\t\t\t<textElement markup=\"none\">\n" +
                            "\t\t\t\t\t<font fontName=\"Times New Roman\" size=\"6\" pdfFontName=\"Helvetica\" pdfEncoding=\"Cp1252\" isPdfEmbedded=\"false\"/>\n" +
                            "\t\t\t\t</textElement>\n" +
                            "\t\t\t\t<text><![CDATA[" + typeP.get(j).getName() + "]]></text>\n" +
                            "\t\t\t</staticText>";

                }
            }

//                    for(int i=0;i<list.size();i++) {
//                        if(list.get(i).)
//                        typeP.remove(list.remove(i));
//                    }
//                    end+="<staticText>\n" +
//                            "                <reportElement x=\""+typeP.get(j).getX()+"\" y=\""+typeP.get(j).getY()+"\" width=\""+typeP.get(j).getWidth()+"\" height=\""+typeP.get(j).getHeight()+"\" uuid=\""+typeP.get(j).getUuid()+"\"/>\n" +
//                            "                <textElement textAlignment=\"Left\">\n" +
//                            "                    <font size=\"6\" fontName=\"Arial\" pdfFontName=\"Helvetica\" pdfEncoding=\"Cp1252\" isPdfEmbedded=\"false\"/>\n" +
//                            "                </textElement>\n" +
//                            "                <text><![CDATA["+typeP.get(j).getName()+"]]></text>\n" +
//                            "            </staticText>";

//            }
            for (int x = 0; x < typeF.size(); x++) {
                end += "<textField>\n" +
                        "\t\t\t\t<reportElement x=\"" + typeF.get(x).getX() + "\" y=\"" + typeF.get(x).getY() + "\" width=\"" + typeF.get(x).getWidth() + "\" height=\"" + typeF.get(x).getHeight() + "\" uuid=\"" + typeF.get(x).getUuid() + "\"/>\n" +
                        "\t\t\t\t<textElement>\n" +
                        "\t\t\t\t\t<font fontName=\"Times New Roman\" size=\"6\" pdfFontName=\"Helvetica\" pdfEncoding=\"Cp1252\" isPdfEmbedded=\"false\"/>\n" +
                        "\t\t\t\t</textElement>\n" +
                        "\t\t\t\t<textFieldExpression><![CDATA[" + typeF.get(x).getName() + "]]></textFieldExpression>\n" +
                        "\t\t\t</textField>";
//                    end+="<textField>\n" +
//                            "                               <reportElement x=\""+typeF.get(x).getX()+"\" y=\""+typeF.get(x).getY()+"\" width=\""+typeF.get(x).getWidth()+"\" height=\""+typeF.get(x).getHeight()+"\" uuid=\""+typeF.get(x).getUuid()+"\"/> +\n" +
//                            "                               <textElement>\n" +
//                            "                                   <font size=\"6\" fontName=\"Arial\" pdfFontName=\"Helvetica\" pdfEncoding=\"Cp1252\" isPdfEmbedded=\"false\"/>\n" +
//                            "                               </textElement>\n" +
//                            "                               <textFieldExpression><![CDATA["+typeF.get(x).getName()+"]]></textFieldExpression>\n" +
//                            "                           </textField>";
            }
//        }
        end+=fooder;
        System.out.println(end);
        InputStream inputStream = new ByteArrayInputStream(end.getBytes());
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list, false);
        Map<String, Object> parameters = new HashMap<>();
        return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
    }

}
