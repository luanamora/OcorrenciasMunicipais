using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class EstadoOcorrenciaMap : IEntityTypeConfiguration<EstadoOcorrencia>
    {
        public void Configure(EntityTypeBuilder<EstadoOcorrencia> entity)
        {
            entity.HasKey(e => e.CdEstadoocorrencia);

            entity.ToTable("estado_ocorrencia");

            entity.Property(e => e.CdEstadoocorrencia).HasColumnName("cd_estadoocorrencia");

            entity.Property(e => e.DsEstadoocorrencia)
                .IsRequired()
                .HasColumnName("ds_estadoocorrencia")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");
        }
    }
}
