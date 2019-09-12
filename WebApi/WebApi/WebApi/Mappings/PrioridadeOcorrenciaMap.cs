using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class PrioridadeOcorrenciaMap : IEntityTypeConfiguration<PrioridadeOcorrencia>
    {
        public void Configure(EntityTypeBuilder<PrioridadeOcorrencia> entity)
        {
            entity.HasKey(e => e.CdPrioridade);

            entity.ToTable("prioridade_ocorrencia");

            entity.Property(e => e.CdPrioridade).HasColumnName("cd_prioridade");

            entity.Property(e => e.DsPrioridade)
                .IsRequired()
                .HasColumnName("ds_prioridade")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.Property(e => e.NrPrioridade).HasColumnName("nr_prioridade");
        }
    }
}
