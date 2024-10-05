package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.RevenueEntity;
import com.Graduation_Be.repository.AdveriserRespository;
import com.Graduation_Be.repository.RevenueRepository;
import com.Graduation_Be.service.impl.AdvertiserServiceImpl;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import com.Graduation_Be.shard.enums.MessageSys;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/advertisement")
@PreAuthorize("hasRole('admin') or hasRole('user')")
public class AdvertisementController {

    @Autowired
    private AdvertiserServiceImpl advertiserService;

    @Autowired
    private AdveriserRespository adveriserRespository;

    @Autowired
    private RevenueRepository revenueRepository;

//    get list
    @GetMapping(value = "")
    public ApiResponse<List<AdvertisementResponseDto>> getAll (){
        return new ApiResponse<>(200, MessageSys.SUSSCESS, advertiserService.getListAdvertiser());
    }

    //    get list
    @GetMapping(value = "/user/{id}")
    public ApiResponse<List<AdvertisementResponseDto>> getAllByUser (@PathVariable long id){
        return new ApiResponse<>(200, MessageSys.SUSSCESS, advertiserService.getListAdvertiserByUser(id));
    }

    @PutMapping(value = "/user/{id}")
    public ApiResponse<AdvertisementResponseDto> updateAdvertiserByUser (@RequestBody AdvertisementRequestDto dto){
        return new ApiResponse<>(200, MessageSys.SUSSCESS, advertiserService.updateAdvertiserByUser(dto));
    }

//  tạo mới
    @PostMapping(value = "")
    public ApiResponse<?> addAdvertisment (@RequestBody AdvertisementCreateRequestDto advertisementCreateRequestDto){
        advertiserService.addAdvertiser(advertisementCreateRequestDto);
        return  new ApiResponse<>(200, MessageSys.SUSSCESS , null);
    }

//    Get one
    @GetMapping(value = "/{id}")
    public ApiResponse<Optional<AdvertisementResponseDto>> getOne(@PathVariable Long id){

        return  new ApiResponse<Optional<AdvertisementResponseDto>>(200, MessageSys.SUSSCESS,advertiserService.getOneAdvertiser(id));
    }

//    update
    @PutMapping(value = "")
    public ApiResponse<AdvertisementResponseDto> updateUser (@RequestBody AdvertisementRequestDto advertisementRequestDto){

        return new ApiResponse<>(200, MessageSys.SUSSCESS, advertiserService.updateAdvertiser(advertisementRequestDto));
    }

//    xóa one
    @DeleteMapping(value = "/{id}")
    public ApiResponse<?> deleteOne(@PathVariable Long id){
        advertiserService.deleteAdvertiser(id);
        return  new ApiResponse<>(200, MessageSys.SUSSCESS,null);
    }

    //    xóa all
    @DeleteMapping(value = "/delAll")
    public ApiResponse<?> deleteAll(){
        advertiserService.deleteAllAdvertiser();
        return  new ApiResponse<>(200, MessageSys.SUSSCESS,null);
    }
// gửi phê duyệt quảng cáo
    @PostMapping(value = "/{id}/request-approval")
    public ApiResponse<?>requestApproval(@PathVariable Long id){
        advertiserService.requestApproval(id);
        return new ApiResponse<>(200, MessageSys.SUSSCESS, null);
    }

//    admin phê duyệt quảng cáo
    @PreAuthorize("hasRole('admin')")
    @PutMapping(value = "/{id}/approve")
    public ApiResponse<?> approveAdvertisement(@PathVariable Long id) {
        advertiserService.approveAdvertisement(id);
        return new ApiResponse<>(200, MessageSys.SUSSCESS, null);
    }

//    lấy doanh thu của 1 quảng cáo
    @GetMapping("/{id}/revenue")
    public ApiResponse<RevenueResponseDto> getAdvertisementRevenue(@PathVariable Long id) {
        RevenueResponseDto revenueDto = advertiserService.getAdvertisementRevenue(id);
        return new ApiResponse<>(200, MessageSys.SUSSCESS, revenueDto);
    }
//    lấy doanh thu của tất cả quảng cáo
    @GetMapping("/revenue")
    public ApiResponse<BigDecimal> getAllRevenue() {
        BigDecimal revenue = advertiserService.getAllRevenue();
        return new ApiResponse<>(200, MessageSys.SUSSCESS,revenue);
    }

//    xuất file
    @GetMapping("/export-advertisements")
    public ResponseEntity<ByteArrayResource> exportAdvertisements() throws IOException {
        List<AdvertisementEntity> advertisements = adveriserRespository.findByStatus(AdvertisementStatus.APPROVED);

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);
        Sheet sheet = workbook.createSheet("Advertisements");

        ((SXSSFSheet) sheet).trackAllColumnsForAutoSizing();

        // Create cell styles for header and data
        CellStyle headerStyle = workbook.createCellStyle();
        CellStyle dataStyle = workbook.createCellStyle();

        // Set border for all sides
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Name", "Price", "Revenue", "Date"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Populate data rows
        int rowNum = 1;
        for (AdvertisementEntity ad : advertisements) {
            Row row = sheet.createRow(rowNum++);

            Cell idCell = row.createCell(0);
            idCell.setCellValue(ad.getAdvertisementId());
            idCell.setCellStyle(dataStyle);

            Cell nameCell = row.createCell(1);
            nameCell.setCellValue(ad.getAdvertisementName());
            nameCell.setCellStyle(dataStyle);

            Cell priceCell = row.createCell(2);
            priceCell.setCellValue(ad.getPrice().doubleValue());
            priceCell.setCellStyle(dataStyle);

            RevenueEntity revenueEntity = revenueRepository.findByAdvertisement(ad);
            if (revenueEntity == null) {
                revenueEntity = new RevenueEntity();
            }
            if (revenueEntity.getAmount() == null) {
                revenueEntity.setAmount(BigDecimal.ZERO);
            }
            if (revenueEntity.getDate() == null) {
                revenueEntity.setDate(LocalDate.now());
            }

            Cell revenueCell = row.createCell(3);
            revenueCell.setCellValue(revenueEntity.getAmount().doubleValue());
            revenueCell.setCellStyle(dataStyle);

            Cell dateCell = row.createCell(4);
            dateCell.setCellValue(revenueEntity.getDate().toString());
            dateCell.setCellStyle(dataStyle);
        }

        // Auto-size columns
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.dispose();

        // Prepare response
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=advertisements.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .contentLength(resource.contentLength())
                .body(resource);
    }

}
