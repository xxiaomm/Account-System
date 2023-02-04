//package com.example.accountmanagementsystem.converter;
//
//import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
//import jakarta.persistence.*;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@Converter(autoApply = true)
//public class EnumStatusConverter implements AttributeConverter<EnumStatus, String> {
//    @Override
//    public String convertToDatabaseColumn(EnumStatus attribute) {
//        return Optional.ofNullable(attribute).map(EnumStatus::getStatus).orElse(null);
//    }
//
//    @Override
//    public EnumStatus convertToEntityAttribute(String dbData) {
//        return EnumStatus.setStatus(dbData);
//    }
//}
