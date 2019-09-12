using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class TipoOcorrenciaMap : IEntityTypeConfiguration<TipoOcorrencia>
    {
        public void Configure(EntityTypeBuilder<TipoOcorrencia> entity)
        {
            entity.HasKey(e => e.CdTipoocorrencia);

            entity.ToTable("tipo_ocorrencia");

            entity.Property(e => e.CdTipoocorrencia).HasColumnName("cd_tipoocorrencia");

            entity.Property(e => e.DsTipoocorrencia)
                .IsRequired()
                .HasColumnName("ds_tipoocorrencia")
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
