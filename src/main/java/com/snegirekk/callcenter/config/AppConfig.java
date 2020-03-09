package com.snegirekk.callcenter.config;

import com.snegirekk.callcenter.dto.ListCallTaskDto;
import com.snegirekk.callcenter.entity.CallTask;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        Converter<CallTask, ListCallTaskDto> callTaskConverter = new Converter<CallTask, ListCallTaskDto>() {
            @Override
            public ListCallTaskDto convert(MappingContext<CallTask, ListCallTaskDto> mappingContext) {
                CallTask entity = mappingContext.getSource();
                ListCallTaskDto dto = mappingContext.getDestination();

                if (null == dto) {
                    dto = new ListCallTaskDto();
                }

                dto.orderNumber = entity.getOrder().getOrderNumber();
                dto.addedAt = entity.getCreatedAt();

                return dto;
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(callTaskConverter);

        return mapper;
    }
}
